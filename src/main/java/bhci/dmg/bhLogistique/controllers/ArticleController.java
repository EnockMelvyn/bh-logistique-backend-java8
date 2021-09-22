package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles(@RequestParam(required = false) String codeArticle) {

        try {
            List<Article> articles = new ArrayList<Article>();
            if (codeArticle == null) {
                articles = articleService.getAllArticles();
            }
            else {
                articles.add(articleService.getArticleByCode(codeArticle));
            }
            if (articles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idArticle}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long idArticle){
        Article article = new Article();
        article= articleService.getArticleById(idArticle);

        if (article == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

//    @GetMapping()
//    public ResponseEntity<Article> getArticleByLibelle(@RequestParam(required = false) String libelleArticle){
//        Article article = new Article();
//        article= articleService.getArticleByLibelle(libelleArticle);
//
//        if (article == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(article, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        return new ResponseEntity<>(articleService.createArticle(article), HttpStatus.CREATED);
    }

    @PutMapping("/{idArticle}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long idArticle, @RequestBody Article article) {
        return new ResponseEntity<>(articleService.updateArticle(idArticle, article), HttpStatus.OK);
    }
}
