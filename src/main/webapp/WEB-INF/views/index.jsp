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
            transition: transform 0.3s ease;
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .card-body {
            padding: 2rem;
        }
        .card-title {
            color: #1e3c72;
            font-weight: 600;
        }
        .btn-primary {
            background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
            border: none;
            padding: 0.5rem 1.5rem;
        }
        .btn-primary:hover {
            background: linear-gradient(135deg, #2a5298 0%, #1e3c72 100%);
        }
        .welcome-title {
            color: #1e3c72;
            font-weight: 700;
            margin-bottom: 3rem;
        }
    </style>
</head>
<body>
    <c:import url="common/menu.jsp">
        <c:param name="active" value="home"/>
    </c:import>

    <div class="container mt-5">
        <h1 class="text-center welcome-title">
            <i class="fas fa-project-diagram me-2"></i>
            Bem-vindo ao Portfolio Manager
        </h1>
        <div class="row mt-4">
            <div class="col-md-6 mb-4">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="fas fa-user-friends fa-3x mb-3" style="color: #1e3c72;"></i>
                        <h5 class="card-title">Membros</h5>
                        <p class="card-text">Gerencie os membros do seu portfólio.</p>
                        <a href="/membros" class="btn btn-primary">
                            <i class="fas fa-users me-2"></i>Ver Membros
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-4">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="fas fa-project-diagram fa-3x mb-3" style="color: #1e3c72;"></i>
                        <h5 class="card-title">Projetos</h5>
                        <p class="card-text">Gerencie os projetos do seu portfólio.</p>
                        <a href="/projetos" class="btn btn-primary">
                            <i class="fas fa-tasks me-2"></i>Ver Projetos
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 