
package com.senai.sistemaacademia.model;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CronogramaHorarios {

    private final List<HorarioAcademia> horarios =
            new ArrayList<>();

    public void adicionarHorario(HorarioAcademia horario) {
        if (horario == null) {
            throw new IllegalArgumentException(
                    "O horário não pode ser nulo.");
        }

        for (HorarioAcademia existente : horarios) {
            if (existente.conflitaCom(horario)) {
                throw new IllegalArgumentException(
                        "Conflito: o professor já tem uma "
                                + "atividade nesse horário.");
            }
        }

        horarios.add(horario);
    }

    public boolean removerHorario(int id) {
        return horarios.removeIf(
                horario -> horario.getId() == id);
    }

    public List<HorarioAcademia> listarHorarios() {
        List<HorarioAcademia> copia =
                new ArrayList<>(horarios);

        copia.sort(
                Comparator.comparing(
                                HorarioAcademia::getDiaSemana)
                        .thenComparing(
                                HorarioAcademia::getHoraInicio));

        return copia;
    }

    public List<HorarioAcademia> listarPorDia(
            DayOfWeek dia) {

        List<HorarioAcademia> resultado =
                new ArrayList<>();

        for (HorarioAcademia horario : horarios) {
            if (horario.getDiaSemana() == dia) {
                resultado.add(horario);
            }
        }

        resultado.sort(
                Comparator.comparing(
                        HorarioAcademia::getHoraInicio));

        return resultado;
    }

    public void exibirCronograma() {
        System.out.println("\n===== CRONOGRAMA DA ACADEMIA =====");

        if (horarios.isEmpty()) {
            System.out.println("Nenhum horário cadastrado.");
            return;
        }

        for (HorarioAcademia horario : listarHorarios()) {
            System.out.println(horario);
        }

        System.out.println("==================================");
    }

    public int getQuantidadeHorarios() {
        return horarios.size();
    }
}
