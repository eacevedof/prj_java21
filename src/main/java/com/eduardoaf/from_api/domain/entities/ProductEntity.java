package com.eduardoaf.from_api.domain.entities;

import java.util.Date;
import com.eduardoaf.shared.domain.entities.AbstractEntity;
import com.eduardoaf.shared.domain.entities.InterfaceEntity;

public final class ProductEntity extends AbstractEntity implements InterfaceEntity {
    public final int insertPlatform;
    public final int insertUser;
    public final Date insertDate;
    public final String updatePlatform;
    public final int updateUser;
    public final Date updateDate;
    public final String deletePlatform;
    public final String deleteUser;
    public final Date deleteDate;
    public final String cruCsvNote;
    public final int isErpSent;
    public final int isEnabled;
    public final String i;
    public final int id;
    public final int codeErp;
    public final String description;
    public final int idProductBrand;
    public final int idProductFamily;
    public final int idProductSubfamily;
    public final String name;
    public final int idTypeContainer;
    public final int idTypeSize;
    public final double priceCost;
    public final int tax;
    public final double taxAmount;
    public final double priceTax;
    public final double priceRegular;
    public final double priceWholesale;
    public final double priceCustom;
    public final double priceOld;
    public final int stock;
    public final int bonusPoints;
    public final String tagGroup;
    public final String observation;

    // Constructor with all attributes
    public ProductEntity(
        int insertPlatform, int insertUser, Date insertDate, String updatePlatform, int updateUser,
        Date updateDate, String deletePlatform, String deleteUser, Date deleteDate, String cruCsvNote,
        int isErpSent, int isEnabled, String i, int id, int codeErp, String description,
        int idProductBrand, int idProductFamily, int idProductSubfamily, String name,
        int idTypeContainer, int idTypeSize, double priceCost, int tax, double taxAmount,
        double priceTax, double priceRegular, double priceWholesale, double priceCustom,
        double priceOld, int stock, int bonusPoints, String tagGroup, String observation
    ) {
        this.insertPlatform = insertPlatform;
        this.insertUser = insertUser;
        this.insertDate = insertDate;
        this.updatePlatform = updatePlatform;
        this.updateUser = updateUser;
        this.updateDate = updateDate;
        this.deletePlatform = deletePlatform;
        this.deleteUser = deleteUser;
        this.deleteDate = deleteDate;
        this.cruCsvNote = cruCsvNote;
        this.isErpSent = isErpSent;
        this.isEnabled = isEnabled;
        this.i = i;
        this.id = id;
        this.codeErp = codeErp;
        this.description = description;
        this.idProductBrand = idProductBrand;
        this.idProductFamily = idProductFamily;
        this.idProductSubfamily = idProductSubfamily;
        this.name = name;
        this.idTypeContainer = idTypeContainer;
        this.idTypeSize = idTypeSize;
        this.priceCost = priceCost;
        this.tax = tax;
        this.taxAmount = taxAmount;
        this.priceTax = priceTax;
        this.priceRegular = priceRegular;
        this.priceWholesale = priceWholesale;
        this.priceCustom = priceCustom;
        this.priceOld = priceOld;
        this.stock = stock;
        this.bonusPoints = bonusPoints;
        this.tagGroup = tagGroup;
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "Product{" +
                "insertPlatform=" + insertPlatform +
                ", insertUser=" + insertUser +
                ", insertDate=" + insertDate +
                ", updatePlatform='" + updatePlatform + '\'' +
                ", updateUser=" + updateUser +
                ", updateDate=" + updateDate +
                ", deletePlatform='" + deletePlatform + '\'' +
                ", deleteUser='" + deleteUser + '\'' +
                ", deleteDate=" + deleteDate +
                ", cruCsvNote='" + cruCsvNote + '\'' +
                ", isErpSent=" + isErpSent +
                ", isEnabled=" + isEnabled +
                ", i='" + i + '\'' +
                ", id=" + id +
                ", codeErp=" + codeErp +
                ", description='" + description + '\'' +
                ", idProductBrand=" + idProductBrand +
                ", idProductFamily=" + idProductFamily +
                ", idProductSubfamily=" + idProductSubfamily +
                ", name='" + name + '\'' +
                ", idTypeContainer=" + idTypeContainer +
                ", idTypeSize=" + idTypeSize +
                ", priceCost=" + priceCost +
                ", tax=" + tax +
                ", taxAmount=" + taxAmount +
                ", priceTax=" + priceTax +
                ", priceRegular=" + priceRegular +
                ", priceWholesale=" + priceWholesale +
                ", priceCustom=" + priceCustom +
                ", priceOld=" + priceOld +
                ", stock=" + stock +
                ", bonusPoints=" + bonusPoints +
                ", tagGroup='" + tagGroup + '\'' +
                ", observation='" + observation + '\'' +
                '}';
    }
}
