import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import '../assets/css/style-liberty.css';

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/auth/login", {
                email,
                password,
            });

            const token = response.data.token;
            localStorage.setItem("jwtToken", token);

            const loggedUser = await axios.get("http://localhost:9191/api/users/getUserFromToken", {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });

            navigate("/");
        } catch (err) {
            setError("Invalid email or password. Please try again.");
        }
    };

    return (
        <div className="login-page">
            <div className="login-card">
                <h2>Login</h2>
                <form onSubmit={handleLogin}>
                    <div className="form-group">
                        <label>Email:</label>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Password:</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    {error && <p className="error-text">{error}</p>}
                    <button type="submit" className="login-button">Login</button>
                </form>

                {/*Register button*/}
                <p style={{ marginTop: "15px" }}>
                    Don't have an account?{" "}
                    <Link to="/register" className="register-link">Create one</Link>
                </p>
            </div>
        </div>
    );
};

export default Login;
