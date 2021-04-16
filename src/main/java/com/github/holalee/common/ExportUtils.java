package com.github.holalee.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @author gaolingfei
 */
@Slf4j
public class ExportUtils {

    private static final int SHEET_ROW_SIZE = 30000;

    public static Workbook doExport(List<Map<String, Object>> rowData, String colName, String mapKey) {
        String[] colNames = colName.split(",");
        String[] mapKeys = mapKey.split(",");

        // 每次操作1000行写入磁盘，避免内存高占用
        Workbook wb = new SXSSFWorkbook(1000);
        //工作表对象
        Sheet sheet = null;
        //行对象
        Row nRow;
        //列对象
        Cell nCell;

        int rowNo = 0;
        int pageRowNo = 0;

        long startTime = System.currentTimeMillis();
        while (rowNo < rowData.size()) {
            if (rowNo % SHEET_ROW_SIZE == 0) {// 分工作薄，每个保存 SHEET_ROW_SIZE 条
                log.info("工作薄: {}", rowNo / SHEET_ROW_SIZE);
                sheet = wb.createSheet(rowNo + "~" + (rowNo + SHEET_ROW_SIZE));
                sheet = wb.getSheetAt(rowNo / SHEET_ROW_SIZE);
                sheet.createFreezePane(0, 1, 0, 1);
                pageRowNo = 0;
                nRow = sheet.createRow(pageRowNo++);
                Font font = wb.createFont();
                CellStyle cellStyle = wb.createCellStyle();

                // 列头
                for (int j = 0; j < colNames.length; j++) {
                    nCell = nRow.createCell(j);
                    nCell.setCellValue(colNames[j]);
                    font.setBold(true);
                    cellStyle.setFont(font);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    nCell.setCellStyle(cellStyle);
                }
                // 查询条件
            }

            nRow = sheet.createRow(pageRowNo++);
            // 数据行
            for (int j = 0; j < mapKeys.length; j++) {
                nCell = nRow.createCell(j);
                nCell.setCellValue((rowData.get(rowNo).getOrDefault(mapKeys[j], ""))==null?"":String.valueOf(rowData.get(rowNo).getOrDefault(mapKeys[j], "")));
            }
            rowNo++;

            if (rowNo % 10000 == 0) {
                log.info("row no: {}", rowNo);
            }
        }

        return wb;
    }

}
