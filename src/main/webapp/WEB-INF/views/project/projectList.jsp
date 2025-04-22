<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Projetos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .table-container {
            margin: 2rem auto;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        .table-title {
            color: #1e3c72;
            margin-bottom: 2rem;
        }
        .btn-primary {
            background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
            border: none;
        }
        .btn-primary:hover {
            background: linear-gradient(135deg, #2a5298 0%, #1e3c72 100%);
        }
        .table th {
            background-color: #f8f9fa;
            color: #1e3c72;
        }
    </style>
</head>
<body>
    <c:import url="../common/menu.jsp">
        <c:param name="active" value="projetos"/>
    </c:import>

    <div class="container">
        <div class="table-container">
            <h2 class="table-title">
                <i class="fas fa-project-diagram me-2"></i>
                Lista de Projetos
            </h2>
            
            <a href="/projetos/novo" class="btn btn-primary mb-3">
                <i class="fas fa-plus me-2"></i>Novo Projeto
            </a>
            
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Data Início</th>
                        <th>Data Previsão Fim</th>
                        <th>Data Fim</th>
                        <th>Descrição</th>
                        <th>Status</th>
                        <th>Orçamento</th>
                        <th>Risco</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${projetos}" var="projeto">
                        <tr>
                            <td>${projeto.nome}</td>
                            <td>${projeto.dataInicio}</td>
                            <td>${projeto.dataPrevisaoFim}</td>
                            <td>${projeto.dataFim}</td>
                            <td>${projeto.descricao}</td>
                            <td>${projeto.status}</td>
                            <td>${projeto.orcamento}</td>
                            <td>${projeto.risco}</td>
                            <td>
                                <a href="/projetos/${projeto.id}/editar" class="btn btn-sm btn-warning">
                                    <i class="fas fa-edit me-1"></i>Editar
                                </a>
                                <a href="/projetos/${projeto.id}/excluir" class="btn btn-sm btn-danger" 
                                   onclick="return confirm('Tem certeza que deseja excluir este projeto?')">
                                    <i class="fas fa-trash me-1"></i>Excluir
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 