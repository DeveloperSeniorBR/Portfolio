<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Membros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Lista de Membros</h1>
        
        <a href="/membros/novo" class="btn btn-primary mb-3">Novo Membro</a>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Pessoa</th>
                    <th>Projeto</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${membros}" var="membro">
                    <tr>
                        <td>${membro.pessoa.nome}</td>
                        <td>${membro.projeto.nome}</td>
                        <td>
                            <a href="/membros/${membro.id}/editar" class="btn btn-sm btn-warning">Editar</a>
                            <a href="/membros/${membro.id}/excluir" class="btn btn-sm btn-danger" 
                               onclick="return confirm('Tem certeza que deseja excluir este membro?')">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 