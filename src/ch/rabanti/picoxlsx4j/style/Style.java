/*
 * PicoXLSX4j is a small Java library to generate XLSX (Microsoft Excel 2007 or newer) files in an easy and native way
 * Copyright Raphael Stoeckli © 2017
 * This library is licensed under the MIT License.
 * You find a copy of the license in project folder or on: http://opensource.org/licenses/MIT
 */
package ch.rabanti.picoxlsx4j.style;

import ch.rabanti.picoxlsx4j.exception.StyleException;

/**
 * Class representing a style which consists of several components
 * @author Raphael Stoeckli
 */
public class Style extends AbstractStyle
{
    
// ### P R I V A T E  F I E L D S ###
    private Border borderRef;
    private CellXf cellXfRef;
    private Fill fillRef;
    private Font fontRef;
    private NumberFormat numberFormatRef;
    private StyleManager styleManagerReference = null;
    private String name;
    private boolean internalStyle;
    private boolean styleNameDefined;

// ### G E T T E R S  &  S E T T E R S ###
    /**
     * Gets the border component of the style
     * @return Border of the style
     */
    public Border getBorder() {
        return borderRef;
    }

    /**
     * Gets the cellXf component of the style
     * @return CellXf of the style
     */
    public CellXf getCellXf() {
        return cellXfRef;
    }

    /**
     * Gets the fill component of the style
     * @return Fill of the style
     */
    public Fill getFill() {
        return fillRef;
    }

    /**
     * Gets the font component of the style
     * @return Font of the style
     */
    public Font getFont() {
        return fontRef;
    }

    /**
     * Gets the number format component of the style
     * @return Number format of the style
     */
    public NumberFormat getNumberFormat() {
        return numberFormatRef;
    }

    /**
     * Sets the border component of the style
     * @param borderRef Border of the style
     */
    public void setBorder(Border borderRef) {
        this.borderRef = borderRef;
        reorganizeStyle();
    }

    /**
     * Sets the cellXf component of the style
     * @param cellXfRef CellXf of the style
     */
    public void setCellXf(CellXf cellXfRef) {
        this.cellXfRef = cellXfRef;
        reorganizeStyle();
    }

    /**
     * Sets the fill component of the style
     * @param fillRef Fill of the style
     */
    public void setFill(Fill fillRef) {
        this.fillRef = fillRef;
        reorganizeStyle();
    }

    /**
     * Sets the font component of the style
     * @param fontRef Font of the style
     */
    public void setFont(Font fontRef) {
        this.fontRef = fontRef;
        reorganizeStyle();
    }

    /**
     * Sets the number format component of the style
     * @param numberFormatRef Number format of the style
     */
    public void setNumberFormat(NumberFormat numberFormatRef) {
        this.numberFormatRef = numberFormatRef;
        reorganizeStyle();
    }

    /**
     * Sets the reference of the style manager
     * @param styleManagerReference Reference to the corresponding style manager object
     */
    public void setStyleManagerReference(StyleManager styleManagerReference) {
        this.styleManagerReference = styleManagerReference;
        reorganizeStyle();
    }

    /**
     * Gets the name of the style
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the style
     * @param name Name 
     */
    public void setName(String name) {
        this.name = name;
        this.styleNameDefined = true;
    }

    /**
     * Gets whether the style is system internal
     * @return If true, the style is an internal style
     */
    public boolean isInternalStyle() {
        return internalStyle;
    }
    
// ### C O N S T R U C T O R S ###   
    /**
     * Default constructor
     */
    public Style()
    {
        this.borderRef = new Border();
        this.cellXfRef = new CellXf();
        this.fillRef = new Fill();
        this.fontRef = new Font();
        this.numberFormatRef = new NumberFormat();
        this.styleNameDefined = false;
        this.name = this.calculateHash();
    }
    
    /**
     * Constructor with parameters
     * @param name Name of the style
     */
    public Style(String name)
    {
        this.borderRef = new Border();
        this.cellXfRef = new CellXf();
        this.fillRef = new Fill();
        this.fontRef = new Font();
        this.numberFormatRef = new NumberFormat();
        this.styleNameDefined = false;
        this.name = name;
    }
    
    /**
     * Constructor with parameters (internal use)
     * @param name Name of the style
     * @param forcedOrder Number of the style for sorting purpose. Style will be placed to this position (internal use only)
     * @param internal If true, the style is marked as internal
     */
    public Style(String name, int forcedOrder, boolean internal)
    {
        this.borderRef = new Border();
        this.cellXfRef = new CellXf();
        this.fillRef = new Fill();
        this.fontRef = new Font();
        this.numberFormatRef = new NumberFormat();
        this.name = name;
        this.setInternalID(forcedOrder);
        this.internalStyle = internal;
        this.styleNameDefined = true;
    }
    
// ### M E T H O D S ###
    /**
     * Method to reorganize / synchronize the components of this style
     */
    private void reorganizeStyle()
    {
        if (this.styleManagerReference == null) { return; }
        else
        {
           Style newStyle = this.styleManagerReference.addStyle(this);
           this.borderRef = newStyle.getBorder();
           this.cellXfRef = newStyle.getCellXf();
           this.fillRef = newStyle.getFill();
           this.fontRef = newStyle.getFont();
           this.numberFormatRef = newStyle.getNumberFormat();
        }
        if (this.styleNameDefined == false)
        {
            this.name = this.calculateHash();
        }
    }
    
     /**
     * Override toString method
     * @return String of a class instance
     */
    @Override
    public String toString()
    {
        return this.getInternalID() + "->" + this.getHash();
    }
    
    /**
     * Override method to calculate the hash of this component
     * @return Calculated hash as string
     */
    @Override
    String calculateHash()
    {
        StringBuilder sb = new StringBuilder();
        if (borderRef == null || cellXfRef == null || fillRef == null || fontRef == null || numberFormatRef == null)
        {
            throw new StyleException("MissingReferenceException","The hash of the style could not be created because one or more components are missing as references");
        }
        sb.append(StyleManager.STYLEPREFIX);
        if (this.getInternalID() != null)
        {
            sb.append(this.getInternalID());
            sb.append(':');
        }
        sb.append(borderRef.calculateHash());
        sb.append(cellXfRef.calculateHash());
        sb.append(fillRef.calculateHash());
        sb.append(fontRef.calculateHash());
        sb.append(numberFormatRef.calculateHash());
        return sb.toString();
    }

    /**
     * Method to copy the current object to a new one
     * @return Copy of the current object without the internal ID
     */          
    @Override
    public AbstractStyle copy()
    {
        if (borderRef == null || cellXfRef == null || fillRef == null || fontRef == null || numberFormatRef == null)
        {
            throw new StyleException("MissingReferenceException","The style could not be copied because one or more components are missing as references");
        }
        Style copy  = new Style();
        copy.setBorder(this.borderRef.copy());
        copy.setCellXf(this.cellXfRef.copy());
        copy.setFill(this.fillRef.copy());
        copy.setFont(this.fontRef.copy());
        copy.setNumberFormat(this.numberFormatRef.copy());
        return copy;
    }
    
    /**
     * Copies the current object as casted style
     * @return Copy of the current object without the internal ID
     */
    public Style copyStyle()
    {
        return (Style)this.copy();
    }
    
}
