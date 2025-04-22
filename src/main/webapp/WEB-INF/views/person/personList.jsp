<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Pessoas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Lista de Pessoas</h1>
        
        <div class="mb-3">
            <a href="/pessoas/novo" class="btn btn-primary">Nova Pessoa</a>
            <a href="/" class="btn btn-secondary">Voltar</a>
        </div>

        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Data Nascimento</th>
                    <th>Funcionário</th>
                    <th>Gerente</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pessoas}" var="pessoa">
                    <tr>
                        <td>${pessoa.id}</td>
                        <td>${pessoa.nome}</td>
                        <td>${pessoa.cpf}</td>
                        <td>${pessoa.dataNascimento}</td>
                        <td>${pessoa.funcionario ? 'Sim' : 'Não'}</td>
                        <td>${pessoa.gerente ? 'Sim' : 'Não'}</td>
                        <td>
                            <a href="/pessoas/${pessoa.id}/editar" class="btn btn-sm btn-warning">Editar</a>
                            <form action="/pessoas/${pessoa.id}/excluir" method="post" style="display: inline;">
                                <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Tem certeza que deseja excluir?')">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 