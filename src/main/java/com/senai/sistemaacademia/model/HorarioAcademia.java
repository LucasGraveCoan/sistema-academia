
package com.senai.sistemaacademia.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class HorarioAcademia {

    private int id;
    private String atividade;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Professor professor;

    public HorarioAcademia(
            int id,
            String atividade,
            DayOfWeek diaSemana,
            LocalTime horaInicio,
            LocalTime horaFim,
            Professor professor) {

        if (atividade == null || atividade.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe o nome da atividade.");
        }

        if (diaSemana == null || horaInicio == null
                || horaFim == null) {
            throw new IllegalArgumentException(
                    "Dia e horários são obrigatórios.");
        }

        if (!horaFim.isAfter(horaInicio)) {
            throw new IllegalArgumentException(
                    "O horário final deve ser depois do inicial.");
        }

        if (professor == null) {
            throw new IllegalArgumentException(
                    "Informe o professor responsável.");
        }

        this.id = id;
        this.atividade = atividade;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public String getAtividade() {
        return atividade;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public Professor getProfessor() {
        return professor;
    }

    public boolean conflitaCom(HorarioAcademia outro) {
        if (outro == null || diaSemana != outro.diaSemana) {
            return false;
        }

        boolean mesmoProfessor = professor == outro.professor;

        boolean horariosSobrepostos =
                horaInicio.isBefore(outro.horaFim)
                        && outro.horaInicio.isBefore(horaFim);

        return mesmoProfessor && horariosSobrepostos;
    }

    @Override
    public String toString() {
        return diaSemana
                + " | " + horaInicio + " - " + horaFim
                + " | " + atividade
                + " | Professor: " + professor.getNome();
    }
}
