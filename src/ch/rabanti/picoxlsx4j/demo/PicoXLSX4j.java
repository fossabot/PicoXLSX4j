/*
 * PicoXLSX4j is a small Java library to generate XLSX (Microsoft Excel 2007 or newer) files in an easy and native way
 * Copyright Raphael Stoeckli © 2018
 * This library is licensed under the MIT License.
 * You find a copy of the license in project folder or on: http://opensource.org/licenses/MIT
 */
package ch.rabanti.picoxlsx4j.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ch.rabanti.picoxlsx4j.Address;
import ch.rabanti.picoxlsx4j.BasicFormulas;
import ch.rabanti.picoxlsx4j.Cell;
import ch.rabanti.picoxlsx4j.Range;
import ch.rabanti.picoxlsx4j.Workbook;
import ch.rabanti.picoxlsx4j.Worksheet;
import ch.rabanti.picoxlsx4j.style.BasicStyles;
import ch.rabanti.picoxlsx4j.style.CellXf;
import ch.rabanti.picoxlsx4j.style.Fill;
import ch.rabanti.picoxlsx4j.style.Style;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * Demo Program for PicoXLSX4j
 * @author Raphael Stoeckli
 */
public class PicoXLSX4j {

    /**
     * Method to run all demos 
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args)  {
       
        basicDemo();
        shortenerDemo();
        streamDemo();
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
        demo7();
        demo8();
        demo9();
    }
    
        /**
         * This is a very basic demo (adding three values and save the workbook)
         */
        private static void basicDemo()
        {

            Workbook workbook = new Workbook("basic.xlsx", "Sheet1");           // Create new workbook
            workbook.getCurrentWorksheet().addNextCell("Test");                 // Add cell A1
            workbook.getCurrentWorksheet().addNextCell("Test2");                // Add cell B1
            workbook.getCurrentWorksheet().addNextCell("Test3");                // Add cell C1
            try
            {
            workbook.save();
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        
        /**
         * This method show the shortened style of writing cells
         */
        private static void shortenerDemo()
        {
            Workbook wb = new Workbook("shortenerDemo.xlsx", "Sheet1"); // Create a workbook (important: A worksheet must be created as well) 
            wb.WS.value("Some Text");                                   // Add cell A1
            wb.WS.value(58.55, BasicStyles.DoubleUnderline());          // Add a formated value to cell B1
            wb.WS.right(2);                                             // Move to cell E1   
            wb.WS.value(true);                                          // Add cell E1
            wb.addWorksheet("Sheet2");                                  // Add a new worksheet
            wb.getCurrentWorksheet().setCurrentCellDirection(Worksheet.CellDirection.RowToRow); // Change the cell direction
            wb.WS.value("This is another text");                        // Add cell A1
            wb.WS.formula("=A1");                                       // Add a formula in Cell A2
            wb.WS.down();                                               // Go to cell A4
            wb.WS.value("Formated Text", BasicStyles.Bold());           // Add a formated value to cell A4
            try
            {
                wb.save();                                              // Save the workbook
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }        
        
        /**
         * This method shows how to save a workbook as stream 
         */
        private static void streamDemo()
        {
            Workbook workbook = new Workbook(true);                             // Create new workbook without file name
            workbook.getCurrentWorksheet().addNextCell("This is an example");   // Add cell A1
            workbook.getCurrentWorksheet().addNextCellFormula("=A1");           // Add formula in cell B1
            workbook.getCurrentWorksheet().addNextCell(123456789);              // Add cell C1
            FileOutputStream fs;                                                // Define a stream
            try
            {
                fs = new FileOutputStream("stream.xlsx");                       // Create a file output stream (could be whatever output stream you want)
                workbook.saveAsStream(fs);                                      // Save the workbook into the stream    
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
 
        /**
         * This method shows the usage of AddNextCell with several data types and formulas
         */
        private static void demo1()
        {
            Workbook workbook = new Workbook("test1.xlsx", "Sheet1");           // Create new workbook
            workbook.getCurrentWorksheet().addNextCell("Test");                 // Add cell A1
            workbook.getCurrentWorksheet().addNextCell(123);                    // Add cell B1
            workbook.getCurrentWorksheet().addNextCell(true);                   // Add cell C1
            workbook.getCurrentWorksheet().goToNextRow();                       // Go to Row 2
            workbook.getCurrentWorksheet().addNextCell(123.456d);               // Add cell A2
            workbook.getCurrentWorksheet().addNextCell(123.789f);               // Add cell B2
            workbook.getCurrentWorksheet().addNextCell(new Date());             // Add cell C2
            workbook.getCurrentWorksheet().goToNextRow();                       // Go to Row 3
            workbook.getCurrentWorksheet().addNextCellFormula("B1*22");         // Add cell A3 as formula (B1 times 22)
            workbook.getCurrentWorksheet().addNextCellFormula("ROUNDDOWN(A2,1)"); // Add cell B3 as formula (Floor A2 with one decimal place)
            workbook.getCurrentWorksheet().addNextCellFormula("PI()");          // Add cell C3 as formula (Pi = 3.14.... )
            try
            {
            workbook.save();                                                    // Save the workbook
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }            
     
        /**
         * This demo shows the usage of several data types, the method AddCell, more than one worksheet and the SaveAs method
         */
        private static void demo2()
        {         
            Workbook workbook = new Workbook(false);                            // Create new workbook
            workbook.addWorksheet("Sheet1");                                    // Add a new Worksheet and set it as current sheet
            workbook.getCurrentWorksheet().addNextCell("月曜日");                // Add cell A1 (Unicode)
            workbook.getCurrentWorksheet().addNextCell(-987);                   // Add cell B1
            workbook.getCurrentWorksheet().addNextCell(false);                  // Add cell C1
            workbook.getCurrentWorksheet().goToNextRow();                       // Go to Row 2
            workbook.getCurrentWorksheet().addNextCell(-123.456d);              // Add cell A2
            workbook.getCurrentWorksheet().addNextCell(-123.789f);              // Add cell B2
            workbook.getCurrentWorksheet().addNextCell(new Date());             // Add cell C3
            workbook.addWorksheet("Sheet2");                                    // Add a new Worksheet and set it as current sheet
            workbook.getCurrentWorksheet().addCell("ABC", "A1");                // Add cell A1
            workbook.getCurrentWorksheet().addCell(779, 2, 1);                  // Add cell C2 (zero based addresses: column 2=C, row 1=2)
            workbook.getCurrentWorksheet().addCell(false, 3, 2);                // Add cell D3 (zero based addresses: column 3=D, row 2=3)
            workbook.getCurrentWorksheet().addNextCell(0);                      // Add cell E3 (direction: column to column)
            List<Object> values = new ArrayList<>();                            // Create a List of mixed values
            values.add("V1");
            values.add(true);
            values.add(16.8);
            workbook.getCurrentWorksheet().addCellRange(values, "A4:C4"); // Add a cell range to A4 - C4
            try
            {   
            workbook.saveAs("test2.xlsx");                                     // Save the workbook
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }            
        }
        
        /**
         * This demo shows the usage of flipped direction when using AddnextCell, reading of the current cell address, and retrieving of cell values
         */
        private static void demo3()
        {
            Workbook workbook = new Workbook("test3.xlsx", "Sheet1");           // Create new workbook
            workbook.getCurrentWorksheet().setCurrentCellDirection(Worksheet.CellDirection.RowToRow);  // Change the cell direction
            workbook.getCurrentWorksheet().addNextCell(1);                      // Add cell A1
            workbook.getCurrentWorksheet().addNextCell(2);                      // Add cell A2
            workbook.getCurrentWorksheet().addNextCell(3);                      // Add cell A3
            workbook.getCurrentWorksheet().addNextCell(4);                      // Add cell A4
            int row = workbook.getCurrentWorksheet().getCurrentRowAddress();    // Get the row number (will be 4 = row row 5)
            int col = workbook.getCurrentWorksheet().getCurrentColumnAddress(); // Get the columnnuber (will be 0 = column A)
            workbook.getCurrentWorksheet().addNextCell("This cell has the row number " + (row+1) + " and column number " + (col+1));
            workbook.getCurrentWorksheet().goToNextColumn();                    // Go to Column B
            workbook.getCurrentWorksheet().addNextCell("A");                    // Add cell B1
            workbook.getCurrentWorksheet().addNextCell("B");                    // Add cell B2
            workbook.getCurrentWorksheet().addNextCell("C");                    // Add cell B3
            workbook.getCurrentWorksheet().addNextCell("D");                    // Add cell B4
            workbook.getCurrentWorksheet().removeCell("A2");                    // Delete cell A2
            workbook.getCurrentWorksheet().removeCell(1,1);                     // Delete cell B2
            workbook.getCurrentWorksheet().goToNextRow(3);                      // Move 3 rows down
            Object value = workbook.getCurrentWorksheet().getCell(1,2).getValue();  // Gets the value of cell B3
            workbook.getCurrentWorksheet().addNextCell("Value of B3 is: " + value);
            try
            {             
            workbook.save();                                                    // Save the workbook
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            } 
        }
        
        /**
         * This demo shows the usage of several styles, column widths and row heights
         */
        private static void demo4()
        {        
            Workbook workbook = new Workbook("test4.xlsx", "Sheet1");           // Create new workbook
            List<Object> values = new ArrayList<>();                            // Create a List of values
            values.add("Header1");
            values.add("Header2");
            values.add("Header3");
            workbook.getCurrentWorksheet().addCellRange(values, new Address(0,0), new Address(2,0));         // Add a cell range to A4 - C4
            workbook.getCurrentWorksheet().getCells().get("A1").setStyle(BasicStyles.Bold());                // Assign predefined basic style to cell
            workbook.getCurrentWorksheet().getCells().get("B1").setStyle(BasicStyles.Bold());                // Assign predefined basic style to cell
            workbook.getCurrentWorksheet().getCells().get("C1").setStyle(BasicStyles.Bold());                // Assign predefined basic style to cell
            workbook.getCurrentWorksheet().goToNextRow();                       // Go to Row 2
            workbook.getCurrentWorksheet().addNextCell( new Date());            // Add cell A2
            workbook.getCurrentWorksheet().addNextCell(2);                      // Add cell B2
            workbook.getCurrentWorksheet().addNextCell(3);                      // Add cell C2
            workbook.getCurrentWorksheet().goToNextRow();                       // Go to Row 3
            workbook.getCurrentWorksheet().addNextCell(new Date());             // Add cell A3
            workbook.getCurrentWorksheet().addNextCell("B");                    // Add cell B3
            workbook.getCurrentWorksheet().addNextCell("C");                    // Add cell C3

            Style s = new Style();                                              // Create new style
            s.getFill().SetColor("FF22FF11", Fill.FillType.fillColor);          // Set fill color
            s.getFont().setDoubleUnderline(true);                               // Set double underline
            s.getCellXf().setHorizontalAlign(CellXf.HorizontalAlignValue.center);  // Set alignment
            
            Style s2 = s.copyStyle();                                           // Copy the previously defined style
            s2.getFont().setItalic(true);                                       // Change an attribute of the copied style

            workbook.getCurrentWorksheet().getCells().get("B2").setStyle(s);    // Assign style to cell
            workbook.getCurrentWorksheet().goToNextRow();                       // Go to Row 3
            workbook.getCurrentWorksheet().addNextCell(new Date(115, 9 ,3));    // Add cell B1
            workbook.getCurrentWorksheet().addNextCell(true);                   // Add cell B2
            workbook.getCurrentWorksheet().addNextCell(false, s2);              // Add cell B3 with style in the same step 
            workbook.getCurrentWorksheet().getCells().get("C2").setStyle(BasicStyles.BorderFrame());        // Assign predefined basic style to cell

            Style s3 = BasicStyles.Strike();                                    // Create a style from a predefined style
            s3.getCellXf().setTextRotation(45);                                 // Set text rotation
            s3.getCellXf().setVerticalAlign(CellXf.VerticalAlignValue.center);  // Set alignment

            workbook.getCurrentWorksheet().getCells().get("B4").setStyle(s3);   // Assign style to cell

            workbook.getCurrentWorksheet().setColumnWidth(0, 20f);              // Set column width
            workbook.getCurrentWorksheet().setColumnWidth(1, 15f);              // Set column width
            workbook.getCurrentWorksheet().setColumnWidth(2, 25f);              // Set column width
            workbook.getCurrentWorksheet().setRowHeight(0, 20);                 // Set row height
            workbook.getCurrentWorksheet().setRowHeight(1, 30);                 // Set row height
            try
            {    
            workbook.save();                                                    // Save the workbook
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            } 
        }
        
        /**
         * This demo shows the usage of cell ranges, adding and removing styles, and meta data 
         */
        private static void demo5()
        { 
            Workbook workbook = new Workbook("test5.xlsx", "Sheet1");           // Create new workbook
            List<Object> values = new ArrayList<>();                            // Create a List of values
            values.add("Header1");
            values.add("Header2");
            values.add("Header3");
            workbook.getCurrentWorksheet().setActiveStyle(BasicStyles.BorderFrameHeader());    // Assign predefined basic style as active style
            workbook.getCurrentWorksheet().addCellRange(values, "A1:C1"); // Add cell range

            values = new ArrayList<>();                                         // Create a List of values
            values.add("Cell A2");
            values.add("Cell B2");
            values.add("Cell C2");            
            workbook.getCurrentWorksheet().setActiveStyle(BasicStyles.BorderFrame());          // Assign predefined basic style as active style
            workbook.getCurrentWorksheet().addCellRange(values, "A2:C2"); // Add cell range

            values = new ArrayList<>();                                         // Create a List of values
            values.add("Cell A3");
            values.add("Cell B3");
            values.add("Cell C3");            
            workbook.getCurrentWorksheet().addCellRange(values, "A3:C3"); // Add cell range

            values = new ArrayList<>();                                         // Create a List of values
            values.add("Cell A4");
            values.add("Cell B4");
            values.add("Cell C4");            
            workbook.getCurrentWorksheet().clearActiveStyle();                  // Clear the active style 
            workbook.getCurrentWorksheet().addCellRange(values, "A4:C4");       // Add cell range

            workbook.getWorkbookMetadata().setTitle("Test 5");                           // Add meta data to workbook
            workbook.getWorkbookMetadata().setSubject("This is the 5th PicoXLSX test");  // Add meta data to workbook
            workbook.getWorkbookMetadata().setCreator("PicoXLSX");                       // Add meta data to workbook
            workbook.getWorkbookMetadata().setKeywords("Keyword1;Keyword2;Keyword3");    // Add meta data to workbook
            try
            {  
            workbook.save();                                                    // Save the workbook
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            } 
        }
        
        /**
         * This demo shows the usage of merging cells, protecting cells, worksheet password protection and workbook protection
         */
        private static void demo6()
        {             
            Workbook workbook = new Workbook("test6.xlsx", "Sheet1");                                        // Create new workbook
            workbook.getCurrentWorksheet().addNextCell("Mergerd1");                                          // Add cell A1
            workbook.getCurrentWorksheet().mergeCells("A1:C1");                                              // Merge cells from A1 to C1
            workbook.getCurrentWorksheet().goToNextRow();                                                    // Go to next row
            workbook.getCurrentWorksheet().addNextCell(false);                                               // Add cell A2
            workbook.getCurrentWorksheet().mergeCells("A2:D2");                                              // Merge cells from A2 to D1
            workbook.getCurrentWorksheet().goToNextRow();                                                    // Go to next row
            workbook.getCurrentWorksheet().addNextCell("22.2d");                                             // Add cell A3
            workbook.getCurrentWorksheet().mergeCells("A3:E4");                                              // Merge cells from A3 to E4
            workbook.addWorksheet("Protected");                                                              // Add a new worksheet
            workbook.getCurrentWorksheet().addAllowedActionOnSheetProtection(Worksheet.SheetProtectionValue.sort);               // Allow to sort sheet (worksheet is automatically set as protected)
            workbook.getCurrentWorksheet().addAllowedActionOnSheetProtection(Worksheet.SheetProtectionValue.insertRows);         // Allow to insert rows
            workbook.getCurrentWorksheet().addAllowedActionOnSheetProtection(Worksheet.SheetProtectionValue.selectLockedCells);  // Allow to select cells (locked cells caused automatically to select unlocked cells)
            workbook.getCurrentWorksheet().addNextCell("Cell A1");                                           // Add cell A1
            workbook.getCurrentWorksheet().addNextCell("Cell B1");                                           // Add cell B1
            workbook.getCurrentWorksheet().getCells().get("A1").setCellLockedState(false, true);             // Set the locking state of cell A1 (not locked but value is hidden when cell selected)
            workbook.addWorksheet("PWD-Protected");                                                          // Add a new worksheet
            workbook.getCurrentWorksheet().addCell("This worksheet is password protected. The password is:",0,0);  // Add cell A1
            workbook.getCurrentWorksheet().addCell("test123", 0, 1);                                         // Add cell A2
            workbook.getCurrentWorksheet().setSheetProtectionPassword("test123");                            // Set the password "test123"
            workbook.setWorkbookProtection(true, true, true, null);                                          // Set workbook protection (windows locked, structure locked, no password)
            try
            {  
            workbook.save();                                                                                 // Save the workbook            
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }    
        
        /**
         * This demo shows the usage of hiding rows and columns, auto-filter and worksheet name sanitizing
         */
        private static void demo7()
        { 
            Workbook workbook = new Workbook(false);                                                    // Create new workbook without worksheet
            String invalidSheetName = "Sheet?1";                                                        // ? is not allowed in the names of worksheets
            String sanitizedSheetName = Worksheet.sanitizeWorksheetName(invalidSheetName, workbook);    // Method to sanitize a worksheet name (replaces ? with _)
            workbook.addWorksheet(sanitizedSheetName);                                                  // Add new worksheet
            Worksheet ws = workbook.getCurrentWorksheet();                                              // Create reference (shortening)
            List<Object> values = new ArrayList<>();                                                    // Create a List of values
            values.add("Cell A1");                                                                      // set a value
            values.add("Cell B1");                                                                      // set a value
            values.add("Cell C1");                                                                      // set a value
            values.add("Cell D1");                                                                      // set a value
            ws.addCellRange(values, "A1:D1");                                                           // Insert cell range
            values = new ArrayList<>();                                                                 // Create a List of values
            values.add("Cell A2");                                                                      // set a value
            values.add("Cell B2");                                                                      // set a value
            values.add("Cell C2");                                                                      // set a value
            values.add("Cell D2");                                                                      // set a value            
            ws.addCellRange(values, "A2:D2");                                                           // Insert cell range
            values = new ArrayList<>();                                                                 // Create a List of values
            values.add("Cell A3");                                                                      // set a value
            values.add("Cell B3");                                                                      // set a value
            values.add("Cell C3");                                                                      // set a value
            values.add("Cell D3");                                                                      // set a value            
            ws.addCellRange(values, "A3:D3");                                                           // Insert cell range
            ws.addHiddenColumn("C");                                                                    // Hide column C
            ws.addHiddenRow(1);                                                                         // Hider row 2 (zero-based: 1)
            ws.setAutoFilter(1, 3);                                                                     // Set auto-filter for column B to D
            try
            {  
            workbook.saveAs("test7.xlsx");                                                              // Save the workbook            
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }      
        }   
        
        /**
         * This demo shows the usage of cell and worksheet selection, auto-sanitizing of worksheet names
         */
        private static void demo8()
        {
            Workbook workbook = new Workbook("test8.xlsx", "Sheet*1", true);  				// Create new workbook with invalid sheet name (*); Auto-Sanitizing will replace * with _
            workbook.getCurrentWorksheet().addNextCell("Test");              				// Add cell A1
            workbook.getCurrentWorksheet().setSelectedCells("A5:B10");					// Set the selection to the range A5:B10
            workbook.addWorksheet("Sheet2");								// Create new worksheet
            workbook.getCurrentWorksheet().addNextCell("Test2");              				// Add cell A1
            Range range = new Range(new Address(1,1), new Address(3,3));                                // Create a cell range for the selection B2:D4
            workbook.getCurrentWorksheet().setSelectedCells(range);					// Set the selection to the range
            workbook.addWorksheet("Sheet2", true);							// Create new worksheet with already existing name; The name will be changed to Sheet21 due to auto-sanitizing (appending of 1) 
            workbook.getCurrentWorksheet().addNextCell("Test3");              				// Add cell A1
            workbook.getCurrentWorksheet().setSelectedCells(new Address(2,2), new Address(4,4));	// Set the selection to the range C3:E5
            workbook.setSelectedWorksheet(1);								// Set the second Tab as selected (zero-based: 1)
            try
            {  
            workbook.save();                                                                            // Save the workbook            
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }                                            				// Save the workbook
        } 
        
        /**
         * This demo shows the usage of basic Excel formulas
         */
        private static void demo9()
        {
            Workbook workbook = new Workbook("test9.xlsx", "sheet1");                                   // Create a new workbook 
            List<Object> numbers = Arrays.asList(1.15d, 2.225d, 13.8d, 15d, 15.1d, 17.22d, 22d, 107.5d, 128d ); // Create a list of numbers
            List<Object> texts = Arrays.asList( "value 1", "value 2", "value 3", "value 4", "value 5", "value 6", "value 7", "value 8", "value 9" ); // Create a list of strings (for vlookup)
            workbook.WS.value("Numbers", BasicStyles.Bold());                                           // Add a header with a basic style
            workbook.WS.value("Values", BasicStyles.Bold());                                            // Add a header with a basic style
            workbook.WS.value("Formula type", BasicStyles.Bold());                                      // Add a header with a basic style
            workbook.WS.value("Formula value", BasicStyles.Bold());                                     // Add a header with a basic style
            workbook.WS.value("(See also worksheet2)");                                                 // Add a note
            workbook.getCurrentWorksheet().addCellRange(numbers, "A2:A10");                             // Add the numbers as range
            workbook.getCurrentWorksheet().addCellRange(texts, "B2:B10");                               // Add the values as range

            workbook.getCurrentWorksheet().setCurrentCellAddress("D2");                                 // Set the "cursor" to D2
            Cell c;                                                                                     // Create an empty cell object (reusable)
            c = BasicFormulas.Average(new Range("A2:A10"));                                             // Define an average formula
            workbook.getCurrentWorksheet().addCell("Average", "C2");                                    // Add the description of the formula to the worksheet
            workbook.getCurrentWorksheet().addCell(c, "D2");                                            // Add the formula to the worksheet

            c = BasicFormulas.Ceil(new Address("A2"), 0);                                               // Define a ceil formula
            workbook.getCurrentWorksheet().addCell("Ceil", "C3");                                       // Add the description of the formula to the worksheet
            workbook.getCurrentWorksheet().addCell(c, "D3");                                            // Add the formula to the worksheet

            c = BasicFormulas.Floor(new Address("A2"), 0);                                              // Define a floor formula
            workbook.getCurrentWorksheet().addCell("Floor", "C4");                                      // Add the description of the formula to the worksheet
            workbook.getCurrentWorksheet().addCell(c, "D4");                                            // Add the formula to the worksheet

            c = BasicFormulas.Round(new Address("A3"), 1);                                              // Define a round formula with one digit after the comma
            workbook.getCurrentWorksheet().addCell("Round", "C5");                                      // Add the description of the formula to the worksheet
            workbook.getCurrentWorksheet().addCell(c, "D5");                                            // Add the formula to the worksheet

            c = BasicFormulas.Max(new Range("A2:A10"));                                                 // Define a max formula
            workbook.getCurrentWorksheet().addCell("Max", "C6");                                        // Add the description of the formula to the worksheet
            workbook.getCurrentWorksheet().addCell(c, "D6");                                            // Add the formula to the worksheet

            c = BasicFormulas.Min(new Range("A2:A10"));                                                 // Define a min formula
            workbook.getCurrentWorksheet().addCell("Min", "C7");                                        // Add the description of the formula to the worksheet
            workbook.getCurrentWorksheet().addCell(c, "D7");                                            // Add the formula to the worksheet

            c = BasicFormulas.Median(new Range("A2:A10"));                                              // Define a median formula
            workbook.getCurrentWorksheet().addCell("Median", "C8");                                     // Add the description of the formula to the worksheet
            workbook.getCurrentWorksheet().addCell(c, "D8");                                            // Add the formula to the worksheet

            c = BasicFormulas.Sum(new Range("A2:A10"));                                                 // Define a sum formula
            workbook.getCurrentWorksheet().addCell("Sum", "C9");                                        // Add the description of the formula to the worksheet
            workbook.getCurrentWorksheet().addCell(c, "D9");                                            // Add the formula to the worksheet

            c = BasicFormulas.VLookup(13.8d, new Range("A2:B10"), 2, true);                             // Define a vlookup formula (look for the value of the number 13.8) 
            workbook.getCurrentWorksheet().addCell("Vlookup", "C10");                                   // Add the description of the formula to the worksheet
            workbook.getCurrentWorksheet().addCell(c, "D10");                                           // Add the formula to the worksheet

            workbook.addWorksheet("sheet2");                                                            // Create a new worksheet
            c = BasicFormulas.VLookup(workbook.getWorksheets().get(0), new Address("B4"), workbook.getWorksheets().get(0), new Range("B2:C10"), 2, true); // Define a vlookup formula in worksheet1 (look for the text right of the (value of) cell B4) 
            workbook.WS.value(c);                                                                       // Add the formula to the worksheet

            c = BasicFormulas.Median(workbook.getWorksheets().get(0), new Range("A2:A10"));             // Define a median formula in worksheet1
            workbook.WS.value(c);                                                                       // Add the formula to the worksheet

            try
            {
              workbook.save();                                                                          // Save the workbook              
            }
            catch(Exception e)
            {
               System.out.println(e.getMessage()); 
            }
        }        
    
}
