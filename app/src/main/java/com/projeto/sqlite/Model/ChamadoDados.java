package com.projeto.sqlite.Model;

/**
 * Created by Andre on 26/05/2017.
 */

public class ChamadoDados {
    private int id;
    private String usuario;
    private Integer idUsuario;
    private String dataInicio;
    private String dataFim;
    private String descricao;
    private Integer idSolucionador;
    private String solucionador;
    private String status;
    private String tipo;



    public ChamadoDados(){}

    public ChamadoDados(int id, String usuario, String descricao, String status, String tipo,
                        String solucionador, String dataInicio, String dataFim) {
        this.id = id;
        this.usuario = usuario;
        this.descricao = descricao;
        this.status = status;
        this.tipo = tipo ;
        this.solucionador = solucionador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
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
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdSolucionador() {
        return idSolucionador;
    }

    public void setIdSolucionador(Integer idSolucionador) {
        this.idSolucionador = idSolucionador;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSolucionador() {
        return solucionador;
    }

    public void setSolucionador(String solucionador) {
        solucionador = solucionador;
    }

    @Override
    public String toString() {
        return "ChamadoDados{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", idUsuario=" + idUsuario +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idSolucionador=" + idSolucionador +
                ", solucionador='" + solucionador + '\'' +
                ", status='" + status + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
