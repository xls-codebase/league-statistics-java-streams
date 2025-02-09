package com.xls.leaguestatistics.view.table;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Table {
    private List<String> columnIdentifiers;
    private List<List<String>> data;

    public Table() {
    }

    public Table(List<List<String>> data, List<String> columnIdentifiers) {
        this.data = data;
        this.columnIdentifiers = columnIdentifiers;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    public void render() {
        List<List<String>> table = new LinkedList<>();
        if (columnIdentifiers != null) {table.add(columnIdentifiers);}
        table.addAll(data);
        List<Integer> columnSizes = IntStream.range(0, table.getFirst().size())
                .mapToObj(i -> table.stream()
                        .map(row -> row.get(i))
                        .max(Comparator.comparingInt(String::length)).get().length()).toList();

        String rowSeparator = String.format("%" + (columnSizes.stream().mapToInt(Integer::intValue).sum() + (columnSizes.size() - 1) * 3 + 4) + "s", " ").replace(' ', '-');
        IntStream.range(0, table.size()).forEach(i -> {
            if (i == 0)  System.out.println(rowSeparator);
            IntStream.range(0, table.get(i).size()).forEach(j -> {
                if (i == 1 && j == 0) System.out.println(rowSeparator);
                System.out.printf((j == 0 ? "| " : "") + "%" + (j == 0 ? "-" : "") + columnSizes.get(j) + "s" + (j == table.getFirst().size() - 1 ? " |" : " | "), table.get(i).get(j));
            });
            System.out.print(System.lineSeparator());
            if (i == table.size() - 1) System.out.println(rowSeparator);
        });
    }
}
