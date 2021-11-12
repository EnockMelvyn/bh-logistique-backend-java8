package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.MouvementStock;
import bhci.dmg.bhLogistique.repository.MouvementStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MouvementStockService {
    @Autowired
    MouvementStockRepository mouvementStockRepository;

    public List<MouvementStock> getAllMouvementStocks(){
        return this.mouvementStockRepository.findAll();
    }

    public List<MouvementStock> getMouvementStockByArticle(Article article) {
        return mouvementStockRepository.findByArticle(article);
    }

    public List<MouvementStock> getMouvementStockByDateMouvement(LocalDate dateMouvement) {
        return mouvementStockRepository.findByDateMouvement(dateMouvement);
    }

    public MouvementStock getMouvementStockById(Long idMouvementStock){
        return  mouvementStockRepository.findById(idMouvementStock).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idMouvementStock +" n'existe pas")
        );
    }

    public MouvementStock deleteMouvementStockById(Long idMouvementStock){
        return  mouvementStockRepository.findById(idMouvementStock).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idMouvementStock +" n'existe pas")
        );
    }

//    public void saveExcel(MultipartFile file) {
//        try {
//            List<String> postesOQP= new ArrayList<>();
//            List<MouvementStock> mouvementStocks = ExcelHelper.excelToMouvementStocks(file.getInputStream());
//            mouvementStockRepository.saveAll(mouvementStocks);
//        } catch (IOException e) {
//            throw new RuntimeException("fail to store excel data: " + e.getMessage());
//        }
//    }
//    public MouvementStock updateMouvementStock(Long idMouvementStock, MouvementStock mouvementStock){
//        MouvementStock mouvementStockToUpdate = mouvementStockRepository.findById(idMouvementStock).orElseThrow(() ->
//                new IllegalStateException(" L'id famille:" + idMouvementStock +" n'existe pas")
//        );
//
//        boolean mouvementStockCodeExists = mouvementStockRepository.findByCodeMouvementStock(mouvementStock.getCodeMouvementStock()).isPresent();
//
//        if (mouvementStockCodeExists) {
//            Long id = mouvementStockRepository.findByCodeMouvementStock(mouvementStock.getCodeMouvementStock()).get().getIdMouvementStock();
//            if (idMouvementStock != id) {
//                throw new IllegalStateException("Ce code sous-famille est déjà utilisé");
//            }
//        }
//
//        mouvementStockToUpdate.setCodeMouvementStock(mouvementStock.getCodeMouvementStock());
//        mouvementStockToUpdate.setNomMouvementStock(mouvementStock.getNomMouvementStock());
//
//        return mouvementStockRepository.save(mouvementStockToUpdate);
//    }

    public List<MouvementStock> createMouvementStock(List<MouvementStock> mouvementStock) {
       // List<MouvementStock> mouvEnregistres = new ArrayList<>();
        // for (MouvementStock mouvementStock1 : mouvementStock) {
        	
        	/*MouvementStock mouvStock = new MouvementStock(mouvementStock1.getDateMouvement(),
                    mouvementStock1.getArticle(),
                    mouvementStock1.getQteAvant(),
                    mouvementStock1.getQteMouvement(),
                    mouvementStock1.getTypeMouvement(),
                    mouvementStock1.getPrixUnitaire());
        	mouvStock.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));*/
        //	mouvEnregistres.add(mouvStock1);
        	
            /*mouvEnregistres.add(new MouvementStock(
                    mouvementStock1.getDateMouvement(),
                    mouvementStock1.getArticle(),
                    mouvementStock1.getQteAvant(),
                    mouvementStock1.getQteMouvement(),
                    mouvementStock1.getTypeMouvement(),
                    mouvementStock1.getPrixUnitaire()
                    
                    )
            );*/
        //}
        // return mouvementStockRepository.saveAll(mouvEnregistres);
        return mouvementStockRepository.saveAll(mouvementStock);
    }
}
