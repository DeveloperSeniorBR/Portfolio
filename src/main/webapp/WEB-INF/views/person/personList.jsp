<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Pessoas</title>
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
        <c:param name="active" value="pessoas"/>
    </c:import>

    <div class="container">
        <div class="table-container">
            <h2 class="table-title">
                <i class="fas fa-users me-2"></i>
                Lista de Pessoas
            </h2>
            
            <a href="/pessoas/novo" class="btn btn-primary mb-3">
                <i class="fas fa-plus me-2"></i>Nova Pessoa
            </a>
            
            <table class="table table-striped">
                <thead>
                    <tr>
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
                            <td>${pessoa.nome}</td>
                            <td>${pessoa.cpf}</td>
                            <td>${pessoa.dataNascimento}</td>
                            <td>${pessoa.funcionario ? 'Sim' : 'Não'}</td>
                            <td>${pessoa.gerente ? 'Sim' : 'Não'}</td>
                            <td>
                                <a href="/pessoas/editar/${pessoa.id}" class="btn btn-sm btn-warning">
                                    <i class="fas fa-edit me-1"></i>Editar
                                </a>
                                <a href="/pessoas/excluir/${pessoa.id}" class="btn btn-sm btn-danger" 
                                   onclick="return confirm('Tem certeza que deseja excluir esta pessoa?')">
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