<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Portfolio Manager</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .card {
            transition: transform 0.2s;
            border: none;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .card-body {
            text-align: center;
        }
        .card-icon {
            font-size: 3rem;
            margin-bottom: 1rem;
            color: #1e3c72;
        }
    </style>
</head>
<body>
    <c:import url="fragments/menu.jsp"/>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <i class="fas fa-users card-icon"></i>
                        <h5 class="card-title">Pessoas</h5>
                        <p class="card-text">Gerencie as pessoas do sistema</p>
                        <a href="/pessoas" class="btn btn-primary">
                            <i class="fas fa-arrow-right me-2"></i>Acessar
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <i class="fas fa-project-diagram card-icon"></i>
                        <h5 class="card-title">Projetos</h5>
                        <p class="card-text">Gerencie os projetos do sistema</p>
                        <a href="/projetos" class="btn btn-primary">
                            <i class="fas fa-arrow-right me-2"></i>Acessar
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <i class="fas fa-user-friends card-icon"></i>
                        <h5 class="card-title">Membros</h5>
                        <p class="card-text">Gerencie os membros dos projetos</p>
                        <a href="/membros" class="btn btn-primary">
                            <i class="fas fa-arrow-right me-2"></i>Acessar
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 