package com.example.Narrow.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Narrow.model.Order;
import com.example.Narrow.model.User;

@Service
public class ExcelGenerator {

    @Autowired
    public OrderService orderService;
    @Autowired
    private UserService userService;

    private XSSFCellStyle headerCellStyle;
    private XSSFCellStyle cellStyle;
    private XSSFCellStyle titleCellStyle;
    private XSSFWorkbook workbook;

    public ByteArrayInputStream customersToExcel(String start, String end, Long id) throws IOException {

	List<Order> orders = orderService.getOrdersRaport(start, end, id);
	if (orders.size() > 0) {
	    String name = "";
	    if (id != null && !id.equals(0)) {
		try {
		    name = ((User) userService.findById(id)).getUsername();
		} catch (Exception e) {
		    System.out.print("Blad");
		}
	    }
	    

	    String[] COLUMNs = { "LP","NR. ZAMÓWIENIA","DATA", "UŻYTKOWNIK", "ARTYKUŁ", "ILOŚĆ","SYMBOL","DOSTAWCA", "KLIENT",
		    "CENA ZAKUPU PLN","CENA ZAKUPU EUR", "CENA SPRZEDAŻY PLN","CENA SPRZEDAŻY EUR", "WARTOŚĆ ZAKUPU PLN","WARTOŚĆ ZAKUPU EUR",
		    "WARTOŚĆ SPRZEDAŻY PLN","WARTOŚĆ SPRZEDAŻY EUR", "ZYSK PLN","ZYSK EUR", };
	    workbook = new XSSFWorkbook();
	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    XSSFSheet sheet = workbook.createSheet("Zamówienia");
	    
	    
	    XSSFCellStyle titleStyle = workbook.createCellStyle();
	    titleStyle.cloneStyleFrom(getTitleCellStyle(workbook));
	    
	    XSSFCellStyle headerStyle = workbook.createCellStyle();
	    headerStyle.cloneStyleFrom(getHeaderCellStyle(workbook));
	    
	    XSSFCellStyle style = workbook.createCellStyle();
	    style.cloneStyleFrom(getCellStyle(workbook));

	    XSSFRow titleRow = sheet.createRow(2);
	    titleRow.setHeightInPoints(20);
	    XSSFCell cell = titleRow.createCell(0);
	    CellRangeAddress zakresKomorekZlecenieProdukcyjne = new CellRangeAddress(cell.getRowIndex(),
		    cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex() + 18);
	    sheet.addMergedRegion(zakresKomorekZlecenieProdukcyjne);
	    StringBuilder sbTitle = new StringBuilder();
	    sbTitle.append("RAPORT ZAMÓWIEŃ Z DNIA OD: ").append(start).append(" DO: ").append(end);
	    if (name != null && !name.equals("")) {
		sbTitle.append(" UŻYTKOWNIKA O LOGINIE: ").append(name.toUpperCase());
	    }
	    cell.setCellValue(sbTitle.toString());
	    cell.setCellStyle(titleStyle);
	    // Row for Header
	    XSSFRow headerRow = sheet.createRow(3);

	    // Header
	    for (int col = 0; col < COLUMNs.length; col++) {
		XSSFCell cells = headerRow.createCell(col);
		cells.setCellValue(COLUMNs[col]);
		cells.setCellStyle(headerStyle);
	    }
	    for (int x = 0; x < sheet.getRow(3).getPhysicalNumberOfCells() + 1; x++) {
		sheet.setColumnWidth(x, x == 0 ? 1000 : 5000);
	    }

	    int rowIdx = 4;
	    int lp = 1;

	    List<HashMap<String, XSSFCell>> kom = new ArrayList<>();
	    for (Order order : orders) {

		HashMap<String, XSSFCell> hm = new HashMap<>();
		XSSFRow row = sheet.createRow(rowIdx++);
		XSSFCell cellLp = row.createCell(0);
		cellLp.setCellValue(lp);
		cellLp.setCellStyle(style);
		
		XSSFCell cellNumber = row.createCell(1);
		cellNumber.setCellValue(order.getNumber());
		cellNumber.setCellStyle(style);
		
		XSSFCell cellDate = row.createCell(2);
		cellDate.setCellValue(order.getCreateDate().toString());
		cellDate.setCellStyle(style);

		XSSFCell cellUser = row.createCell(3);
		cellUser.setCellValue(order.getUser().getUsername());
		cellUser.setCellStyle(style);

		XSSFCell cellArtykul = row.createCell(4);
		cellArtykul.setCellValue("Artykul");
		cellArtykul.setCellStyle(style);
		
		XSSFCell cellQuantityPurchase = row.createCell(5);
		cellQuantityPurchase.setCellValue(Double.valueOf(order.getQuantityPurchase().toString()));
		cellQuantityPurchase.setCellStyle(style);
		cellQuantityPurchase.setCellType(CellType.NUMERIC);

		XSSFCell cellSymbol = row.createCell(6);
		cellSymbol.setCellType(CellType.STRING);
		cellSymbol.setCellValue(order.getSymbol().toString());
		cellSymbol.setCellStyle(style);

		XSSFCell cellProvider = row.createCell(7);
		cellProvider.setCellValue(order.getProvider().getName());
		cellProvider.setCellStyle(style);

		XSSFCell cellCustomer = row.createCell(8);
		cellCustomer.setCellValue(order.getCustomer().getName());
		cellCustomer.setCellStyle(style);

		XSSFCell cellPricePurchasePLN = row.createCell(9);
		cellPricePurchasePLN.setCellValue(
			order.getPricePurchasePLN() != null ? Double.valueOf(order.getPricePurchasePLN().toString())
				: Double.valueOf("0"));
		cellPricePurchasePLN.setCellStyle(style);
		cellPricePurchasePLN.setCellType(CellType.NUMERIC);
		
		XSSFCell cellPricePurchaseEUR = row.createCell(10);
		cellPricePurchaseEUR.setCellValue(
			order.getPricePurchaseEUR() != null ? Double.valueOf(order.getPricePurchaseEUR().toString())
				: Double.valueOf("0"));
		cellPricePurchaseEUR.setCellStyle(style);
		cellPricePurchaseEUR.setCellType(CellType.NUMERIC);

		XSSFCell cellPriceSellPLN = row.createCell(11);
		cellPriceSellPLN.setCellValue(Double.valueOf(order.getPriceSellPLN().toString()));
		cellPriceSellPLN.setCellStyle(style);
		cellPriceSellPLN.setCellType(CellType.NUMERIC);
		
		XSSFCell cellPriceSellEUR = row.createCell(12);
		cellPriceSellEUR.setCellValue(Double.valueOf(order.getPriceSellEUR().toString()));
		cellPriceSellEUR.setCellStyle(style);
		cellPriceSellEUR.setCellType(CellType.NUMERIC);

		XSSFCell cellValuePurchasePLN = row.createCell(13);
		cellValuePurchasePLN.setCellValue(Double.valueOf(order.getValuePurchasePLN().toString()));
		cellValuePurchasePLN.setCellStyle(style);
		cellValuePurchasePLN.setCellType(CellType.NUMERIC);
		hm.put("VALUE_PURCHASE_PLN", cellValuePurchasePLN);
		
		XSSFCell cellValuePurchaseEUR = row.createCell(14);
		cellValuePurchaseEUR.setCellValue(Double.valueOf(order.getValuePurchaseEUR().toString()));
		cellValuePurchaseEUR.setCellStyle(style);
		cellValuePurchaseEUR.setCellType(CellType.NUMERIC);
		hm.put("VALUE_PURCHASE_EUR", cellValuePurchaseEUR);

		XSSFCell cellValueSellPLN = row.createCell(15);
		cellValueSellPLN.setCellValue(Double.valueOf(order.getValueSellPLN().toString()));
		cellValueSellPLN.setCellStyle(style);
		cellValueSellPLN.setCellType(CellType.NUMERIC);
		hm.put("VALUE_SELL_PLN", cellValueSellPLN);
		
		XSSFCell cellValueSellEUR = row.createCell(16);
		cellValueSellEUR.setCellValue(Double.valueOf(order.getValueSellEUR().toString()));
		cellValueSellEUR.setCellStyle(style);
		cellValueSellEUR.setCellType(CellType.NUMERIC);
		hm.put("VALUE_SELL_EUR", cellValueSellEUR);

		XSSFCell cellProfitPLN = row.createCell(17);
		cellProfitPLN.setCellType(CellType.NUMERIC);
		cellProfitPLN.setCellValue(Double.valueOf(order.getProfitPLN().toString()));
		cellProfitPLN.setCellStyle(style);
		hm.put("PROFIT_PLN", cellProfitPLN);
		
		XSSFCell cellProfitEUR = row.createCell(18);
		cellProfitEUR.setCellType(CellType.NUMERIC);
		cellProfitEUR.setCellValue(Double.valueOf(order.getProfitEUR().toString()));
		cellProfitEUR.setCellStyle(style);
		hm.put("PROFIT_EUR", cellProfitEUR);
		
		kom.add(hm);

		lp++;
	    }
	    // Podsumowanie
	    if (!kom.isEmpty()) {
		XSSFCell firstCellProfitPLN = kom.get(0).get("PROFIT_PLN");
		XSSFCell lastCellProfitPLN = kom.get(kom.size() - 1).get("PROFIT_PLN");
		
		XSSFCell firstCellProfitEUR = kom.get(0).get("PROFIT_EUR");
		XSSFCell lastCellProfitEUR = kom.get(kom.size() - 1).get("PROFIT_EUR");
		
		XSSFCell firstCellPurchasePLN = kom.get(0).get("VALUE_PURCHASE_PLN");
		XSSFCell lastCellPurchasePLN = kom.get(kom.size() - 1).get("VALUE_PURCHASE_PLN");
		
		XSSFCell firstCellPurchaseEUR = kom.get(0).get("VALUE_PURCHASE_EUR");
		XSSFCell lastCellPurchaseEUR = kom.get(kom.size() - 1).get("VALUE_PURCHASE_EUR");

		XSSFCell firstCellSellPLN = kom.get(0).get("VALUE_SELL_PLN");
		XSSFCell lastCellSellPLN = kom.get(kom.size() - 1).get("VALUE_SELL_PLN");
		
		XSSFCell firstCellSellEUR = kom.get(0).get("VALUE_SELL_EUR");
		XSSFCell lastCellSellEUR = kom.get(kom.size() - 1).get("VALUE_SELL_EUR");

		
		XSSFRow rowSumary = sheet.createRow(++rowIdx);

		XSSFCell cellSumaryPurchasePLN = rowSumary.createCell(13);
		cellSumaryPurchasePLN.setCellFormula("SUM(" + getKomRefStr(firstCellPurchasePLN, false, true) + ":"
			+ getKomRefStr(lastCellPurchasePLN, false, true) + ")");
		cellSumaryPurchasePLN.setCellStyle(style);

		XSSFCell cellSumaryPurchaseEUR = rowSumary.createCell(14);
		cellSumaryPurchaseEUR.setCellFormula("SUM(" + getKomRefStr(firstCellPurchaseEUR, false, true) + ":"
			+ getKomRefStr(lastCellPurchaseEUR, false, true) + ")");
		cellSumaryPurchaseEUR.setCellStyle(style);
		
		XSSFCell cellSumarySellPLN = rowSumary.createCell(15);
		cellSumarySellPLN.setCellFormula("SUM(" + getKomRefStr(firstCellSellPLN, false, true) + ":"
			+ getKomRefStr(lastCellSellPLN, false, true) + ")");
		cellSumarySellPLN.setCellStyle(style);

		XSSFCell cellSumarySellEUR = rowSumary.createCell(16);
		cellSumarySellEUR.setCellFormula("SUM(" + getKomRefStr(firstCellSellEUR, false, true) + ":"
			+ getKomRefStr(lastCellSellEUR, false, true) + ")");
		cellSumarySellEUR.setCellStyle(style);
		
		XSSFCell cellSumaryProfitPLN = rowSumary.createCell(17);
		cellSumaryProfitPLN.setCellFormula("SUM(" + getKomRefStr(firstCellProfitPLN, false, true) + ":"
			+ getKomRefStr(lastCellProfitPLN, false, true) + ")");
		cellSumaryProfitPLN.setCellStyle(style);
		
		XSSFCell cellSumaryProfitEUR = rowSumary.createCell(18);
		cellSumaryProfitEUR.setCellFormula("SUM(" + getKomRefStr(firstCellProfitEUR, false, true) + ":"
			+ getKomRefStr(lastCellProfitEUR, false, true) + ")");
		cellSumaryProfitEUR.setCellStyle(style);

	    }
	    workbook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());

	} else {
	    return null;
	}

    }

    private String getKomRefStr(XSSFCell passedKomorka, boolean bezwzglednyWiersz, boolean bezwzglednieKolumna) {
	String adresKmomorki = null;
	if (passedKomorka != null) {
	    CellReference refrencjaKomorki = new CellReference(passedKomorka.getRowIndex(),
		    passedKomorka.getColumnIndex(), bezwzglednyWiersz, bezwzglednieKolumna);
	    adresKmomorki = refrencjaKomorki.formatAsString();
	    // Tymczasowy.GetLogger().info("adresKmomorki: " + adresKmomorki);
	}
	return adresKmomorki;
    }

    private XSSFCellStyle getHeaderCellStyle(XSSFWorkbook workBook) {
	if (headerCellStyle == null) {

	    headerCellStyle = workBook.createCellStyle();

	    Font headerFont = workBook.createFont();
	    headerFont.setBold(true);
	    headerFont.setColor(IndexedColors.BLACK.getIndex());

	    headerCellStyle.setFont(headerFont);
	    headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	    headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
	    headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	    headerCellStyle.setBorderBottom(BorderStyle.THIN);
	    headerCellStyle.setBorderTop(BorderStyle.THIN);
	    headerCellStyle.setBorderLeft(BorderStyle.THIN);
	    headerCellStyle.setBorderRight(BorderStyle.THIN);

	}
	return headerCellStyle;
    }

    private XSSFCellStyle getTitleCellStyle(XSSFWorkbook workBook) {
	if (titleCellStyle == null) {
	    titleCellStyle = workBook.createCellStyle();

	    Font headerFont = workBook.createFont();
	    headerFont.setBold(true);
	    headerFont.setColor(IndexedColors.WHITE.getIndex());
	    headerFont.setFontHeightInPoints((short) 15);

	    titleCellStyle.setFont(headerFont);
	    titleCellStyle.setFillForegroundColor(IndexedColors.TEAL.getIndex());
	    titleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
	    titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

	}
	return titleCellStyle;
    }

    private XSSFCellStyle getCellStyle(XSSFWorkbook workBook) {
	if (cellStyle == null) {

	    cellStyle = workBook.createCellStyle();
	    cellStyle.setBorderBottom(BorderStyle.THIN);
	    cellStyle.setBorderTop(BorderStyle.THIN);
	    cellStyle.setBorderLeft(BorderStyle.THIN);
	    cellStyle.setBorderRight(BorderStyle.THIN);
	}
	return cellStyle;
    }
}