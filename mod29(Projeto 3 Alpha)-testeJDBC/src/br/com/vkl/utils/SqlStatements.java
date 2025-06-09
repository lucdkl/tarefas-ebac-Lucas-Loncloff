package br.com.vkl.utils;

public class SqlStatements {

    public static String getTbCliente(){
        return "TB_CLIENTE";
    }

    public static String getTbProduto(){
        return "TB_PRODUTO";
    }

    public static String getSQCliente(){
        return "sq_cliente";
    }


    public static String getSQProduto(){
        return "sq_produto";
    }

    public static String getSqlInsert(String table, String sq) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO "+ table +"(ID, CODIGO, NOME) ");
        sb.append("VALUES (nextval('"+ sq +"'),?,?)");
        return sb.toString();
    }

    public static String getSqlUpdate(String table) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + table + " ");
        sb.append("SET NOME = ?, CODIGO = ? ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }

    public static String getSqlSearch(String table) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM " + table + " ");
        sb.append("WHERE codigo = ?");
        return sb.toString();
    }

    public static String getSqlSearchAll(String table) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM " + table);
        return sb.toString();
    }

    public static String getSqlDelete(String table) {
        StringBuilder sb = new StringBuilder();
        sb.append( "DELETE FROM " + table + " WHERE CODIGO = ?");
        return sb.toString();
    }


}
