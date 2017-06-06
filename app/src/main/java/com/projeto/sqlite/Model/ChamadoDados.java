package com.projeto.sqlite.Model;

/**
 * Created by Andre on 26/05/2017.
 */

public class ChamadoDados {
    private int id;
    private String nomeUsuario;
    private String idUsuario;
    private String dateInicio;
    private String dataFim;
    private String descricao;
    private String idSolucionador;
    private String nomeSolucionador;
    private String status;
    private String tipo;

    public ChamadoDados(){}

    public ChamadoDados(int id, String descricao) {
        this.id = id ;
        this.descricao = descricao;
    }

    public ChamadoDados(String descricao, String tipo, String status){
        this.descricao = descricao;
        this.tipo = tipo;
        this.status = status;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataInicio() {
        return dateInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dateInicio = dataInicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdSolucionador() {
        return idSolucionador;
    }

    public void setIdSolucionador(String idSolucionador) {
        this.idSolucionador = idSolucionador;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return nomeUsuario;
    }

    public void setUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSolucionador() {
        return nomeSolucionador;
    }

    public void setSolucionador(String nomeSolucionador) {
        nomeSolucionador = nomeSolucionador;
    }

    @Override
    public String toString() {
        return "ChamadoDados{" +
                "id=" + id +
                ", usuario='" + nomeUsuario + '\'' +
                ", idUsuario=" + idUsuario +
                ", dataInicio='" + dateInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idSolucionador=" + idSolucionador +
                ", solucionador='" + nomeSolucionador + '\'' +
                ", status='" + status + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
