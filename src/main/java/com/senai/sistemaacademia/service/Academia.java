package com.senai.sistemaacademia.service;
import com.senai.sistemaacademia.model.CronogramaHorarios;
public class Academia {
    private final CronogramaHorarios cronogramaHorarios =
            new CronogramaHorarios();
    public CronogramaHorarios getCronogramaHorarios() {
        return cronogramaHorarios;
    }
    public void exibirCronograma() {
        cronogramaHorarios.exibirCronograma();
    }
    }

