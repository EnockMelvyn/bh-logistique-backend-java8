package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        /* List<Article> articles= new ArrayList<Article>();
        articleRepository.findAll().forEach(articles::add);*/
        return articleRepository.findAll();
    }

    public Article getArticleByCode(String codeArticle) {
        return articleRepository.findByCodeArticle(codeArticle).orElseThrow(() ->
                new IllegalStateException(" Le code sous famille:" + codeArticle +" n'existe pas")
        );
    }

    public Article getArticleByLibelle(String libelleArticle) {
        return articleRepository.findByLibelleArticle(libelleArticle).orElseThrow(() ->
                new IllegalStateException(" Le libelle sous famille:" + libelleArticle +" n'existe pas")
        );
    }

    public Article getArticleById(Long idArticle){
        return  articleRepository.findById(idArticle).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idArticle +" n'existe pas")
        );
    }

    public Article deleteArticleById(Long idArticle){
        return  articleRepository.findById(idArticle).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idArticle +" n'existe pas")
        );
    }

    public Article updateArticle(Long idArticle, Article article){
        Article articleToUpdate = articleRepository.findById(idArticle).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idArticle +" n'existe pas")
        );

        boolean articleCodeExists = articleRepository.findByCodeArticle(article.getCodeArticle()).isPresent();

        if (articleCodeExists) {
            Long id = articleRepository.findByCodeArticle(article.getCodeArticle()).get().getIdArticle();
            if (idArticle != id) {
                throw new IllegalStateException("Ce code sous-famille est déjà utilisé");
            }
        }

        articleToUpdate.setCodeArticle(article.getCodeArticle());
        articleToUpdate.setLibelleArticle(article.getLibelleArticle());
        articleToUpdate.setSousFamille(article.getSousFamille());


        return articleRepository.save(articleToUpdate);
    }

    public Article createArticle(Article article) {
        boolean articleExists = articleRepository.findByCodeArticle(article.getCodeArticle()).isPresent();

        if (articleExists) {
            throw new IllegalStateException("Ce code sous-famille est déjà utilisé");
        }

        return articleRepository.save(new Article(article.getLibelleArticle(), article.getCodeArticle(), article.getSousFamille()));
    }
}
