<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Projetos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Lista de Projetos</h1>
        
        <c:if test="${not empty mensagem}">
            <div class="alert alert-info">${mensagem}</div>
        </c:if>
        
        <a href="/projetos/novo" class="btn btn-primary mb-3">Novo Projeto</a>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Data Início</th>
                    <th>Previsão Término</th>
                    <th>Status</th>
                    <th>Risco</th>
                    <th>Gerente</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${projetos}" var="projeto">
                    <tr>
                        <td>${projeto.nome}</td>
                        <td>${projeto.dataInicio}</td>
                        <td>${projeto.dataPrevisaoFim}</td>
                        <td>${projeto.status.descricao}</td>
                        <td>${projeto.risco.descricao}</td>
                        <td>${projeto.gerente.nome}</td>
                        <td>
                            <a href="/projetos/${projeto.id}/editar" class="btn btn-sm btn-warning">Editar</a>
                            <a href="/projetos/${projeto.id}/excluir" class="btn btn-sm btn-danger" 
                               onclick="return confirm('Tem certeza que deseja excluir este projeto?')">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 