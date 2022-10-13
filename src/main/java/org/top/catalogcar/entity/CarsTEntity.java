package org.top.catalogcar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cars_t", schema = "cars_db", catalog = "")
public class CarsTEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name_f")
    private String nameF;
    @Basic
    @Column(name = "model_f")
    private String modelF;
    @Basic
    @Column(name = "year_f")
    private Integer yearF;
    @Basic
    @Column(name = "price_f")
    private Double priceF;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameF() {
        return nameF;
    }

    public void setNameF(String nameF) {
        this.nameF = nameF;
    }

    public String getModelF() {
        return modelF;
    }

    public void setModelF(String modelF) {
        this.modelF = modelF;
    }

    public Integer getYearF() {
        return yearF;
    }

    public void setYearF(Integer yearF) {
        this.yearF = yearF;
    }

    public Double getPriceF() {
        return priceF;
    }

    public void setPriceF(Double priceF) {
        this.priceF = priceF;
    }

    //копирование полей объекта
    public void updateFields(CarsTEntity src) {
        nameF = src.nameF;
        modelF = src.modelF;
        yearF = src.yearF;
        priceF = src.priceF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarsTEntity that = (CarsTEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nameF != null ? !nameF.equals(that.nameF) : that.nameF != null) return false;
        if (modelF != null ? !modelF.equals(that.modelF) : that.modelF != null) return false;
        if (yearF != null ? !yearF.equals(that.yearF) : that.yearF != null) return false;
        if (priceF != null ? !priceF.equals(that.priceF) : that.priceF != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nameF != null ? nameF.hashCode() : 0);
        result = 31 * result + (modelF != null ? modelF.hashCode() : 0);
        result = 31 * result + (yearF != null ? yearF.hashCode() : 0);
        result = 31 * result + (priceF != null ? priceF.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarsTEntity{" +
                "id=" + id +
                ", nameF='" + nameF + '\'' +
                ", modelF='" + modelF + '\'' +
                ", yearF=" + yearF +
                ", priceF=" + priceF +
                '}';
    }
}
