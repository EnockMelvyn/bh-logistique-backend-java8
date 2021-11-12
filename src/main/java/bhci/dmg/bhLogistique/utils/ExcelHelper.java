package bhci.dmg.bhLogistique.utils;

import bhci.dmg.bhLogistique.dao.MouvementStock;
import bhci.dmg.bhLogistique.enums.TypeMouvement;
import bhci.dmg.bhLogistique.services.ArticleService;
import bhci.dmg.bhLogistique.services.FournisseurService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//    static String[] HEADERs = { "Article", "Ref", "Stock Initial", "Entrées", "Sortie","P.U" };
    static String[] HEADERs = { "Date", "Ref", "Entrée", "Sortie","Marque","Fournisseur","Direction / Agence","Demandeur","Prix Unitaire","Montant Total" };
    static String SHEET = "Journal entrées et sorties";
    private static ArticleService articleService;
    private static FournisseurService fournisseurService;

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<MouvementStock> excelToMouvementStocks(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<MouvementStock> mouvementStocks = new ArrayList<MouvementStock>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                MouvementStock mouvementStock = new MouvementStock();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                    case 0:
                        mouvementStock.setDateMouvement(currentCell.getLocalDateTimeCellValue());
                        break;
                    case 1:
                        try {
                            mouvementStock.setArticle(articleService.getArticleByCode((currentCell.getStringCellValue())));
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    case 2:
                        mouvementStock.setQteMouvement(currentCell.getNumericCellValue());
                        break;
                    case 3:
                        if (currentCell.getNumericCellValue()>0 ){
                            mouvementStock.setTypeMouvement(TypeMouvement.SORTIE);
                        }
                        break;
                    case 4:
                        try {
                            mouvementStock.setFournisseur(fournisseurService.getFournisseurByCode((currentCell.getStringCellValue())));
                            break;
                        } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    case 5:
                        mouvementStock.setDemandeur((currentCell.getStringCellValue()));
                        break;
                    case 6:
                        mouvementStock.setPrixUnitaire(currentCell.getNumericCellValue());
                        break;

                    default:
                        break;
                    }

                    cellIdx++;
                }

                mouvementStocks.add(mouvementStock);
            }

            workbook.close();
            return mouvementStocks;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
