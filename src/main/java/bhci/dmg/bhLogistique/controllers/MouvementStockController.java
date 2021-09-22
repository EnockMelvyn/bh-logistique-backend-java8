package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.MouvementStock;
import bhci.dmg.bhLogistique.message.ResponseMessage;
import bhci.dmg.bhLogistique.services.FournisseurService;
import bhci.dmg.bhLogistique.services.MouvementStockService;
import bhci.dmg.bhLogistique.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/mouvementStock")
public class MouvementStockController {

    @Autowired
    MouvementStockService mouvementStockService;

    @GetMapping
    public ResponseEntity<List<MouvementStock>> getAllMouvementStocks(@RequestParam(required = false) Article article) {

        try {
            List<MouvementStock> mouvementStocks = new ArrayList<MouvementStock>();
            if (article == null) {
                mouvementStocks = mouvementStockService.getAllMouvementStocks();
            }
            else {
                mouvementStocks=mouvementStockService.getMouvementStockByArticle(article);
            }
            if (mouvementStocks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(mouvementStocks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idMouvementStock}")
    public ResponseEntity<MouvementStock> getMouvementStockById(@PathVariable Long idMouvementStock){
        MouvementStock mouvementStock = new MouvementStock();
        mouvementStock= mouvementStockService.getMouvementStockById(idMouvementStock);

        if (mouvementStock == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mouvementStock, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<ResponseMessage> createMouvementStock(@RequestParam("file") MultipartFile file) {
//        String message = "";
//
//        if (ExcelHelper.hasExcelFormat(file)) {
//            try {
//                mouvementStockService.saveExcel(file);
//
//                message = "Fichier uploadé avec succès: " + file.getOriginalFilename();
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//            } catch (Exception e) {
//                message = "Le fichier: " + file.getOriginalFilename() + " n'a pas pu être uploadé!";
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//            }
//        }
//
//        message = "Veuillez uploader un fichier CSV SVP!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//    }

    @PostMapping
    public ResponseEntity<List<MouvementStock>> createMouvementStock(@RequestBody List<MouvementStock> mouvementStock) {
        return new ResponseEntity<>(mouvementStockService.createMouvementStock(mouvementStock), HttpStatus.CREATED);
    }

//    @PutMapping("/{idMouvementStock}")
//    public ResponseEntity<MouvementStock> updateFamille(@PathVariable Long idMouvementStock, @RequestBody MouvementStock mouvementStock) {
//        return new ResponseEntity<>(mouvementStockService.updateMouvementStock(idMouvementStock, mouvementStock), HttpStatus.OK);
//    }
}
