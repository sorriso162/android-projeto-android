package com.projeto.sqlite.Model;

/**
 * Created by Andre on 26/05/2017.
 */

public class ChamadoDados {
    private Integer idUsuario;
    private String dataInicio;
    private String descricao;
    private Integer idSolucionador;
    private String status;
    private String tipo;

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

    @Override
    public String toString() {
        return "ChamadoDados{" +
                "IdUsuario=" + idUsuario +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
