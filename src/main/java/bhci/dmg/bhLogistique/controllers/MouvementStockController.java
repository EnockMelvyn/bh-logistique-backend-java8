package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.MouvementStock;
import bhci.dmg.bhLogistique.services.MouvementStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<List<MouvementStock>> createMouvementStock(@RequestBody List<MouvementStock> mouvementStock) {
        return new ResponseEntity<>(mouvementStockService.createMouvementStock(mouvementStock), HttpStatus.CREATED);
    }
}
