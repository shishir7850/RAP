package com.quadratic.rap.dataimport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.quadratic.rap.common.IConstants;

@Entity
@Table( name = "DATE_FORMATS" )
public class DateFormat
{

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "DATE_FORMAT_ID" )
    private Integer dateFormatId;

    @Column( name = "DATE_FORMAT" )
    private String dateFormat;

    @Column( name = "SOURCE_TYPE" )
    @Enumerated( EnumType.STRING )
    private IConstants.EnumSourceType sourceType;

    public Integer getDateFormatId()
    {
        return dateFormatId;
    }

    public void setDateFormatId( Integer dateFormatId )
    {
        this.dateFormatId = dateFormatId;
    }

    public String getDateFormat()
    {
        return dateFormat;
    }

    public void setDateFormat( String dateFormat )
    {
        this.dateFormat = dateFormat;
    }

    public IConstants.EnumSourceType getSourceType()
    {
        return sourceType;
    }

    public void setSourceType( IConstants.EnumSourceType sourceType )
    {
        this.sourceType = sourceType;
    }

}
