import React, { useEffect, useState } from "react";
import axios from "axios";
import {Link} from "react-router-dom";
import hotelLogo from "../assets/images/hotels.png";

const MyReservation = () => {
    const [reservation, setReservation] = useState(null);
    const [loading, setLoading] = useState(true);

    const token = localStorage.getItem("jwtToken");

    useEffect(() => {
        const fetchData = async () => {
            try {
                // Fetch reservation info
                const res = await axios.get(`/api-gateway/api/reservations`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        'Content-Type': 'application/json'
                    }
                });
                setReservation(res.data);
                console.log(res.data);
            } catch (error) {
                console.error("Error fetching reservation data:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [token]);

    if (loading) return <div>Loading your reservation...</div>;
    if (!reservation) return <div>No reservation found.</div>;

    const {room, reservation: resData, hotelServices } = reservation;
    console.log("room", room);
    console.log("hotelServices", hotelServices);
    console.log("reservation", resData);

    //delete service from reservation
    const handleRemoveService = async (serviceId) => {
        if (!window.confirm("Are you sure you want to remove this service from your reservation?")) return;

        try {
            const response = await axios.delete(
                `/api-gateway/api/reservations/removeHotelService?serviceId=${serviceId}`,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    }
                }
            );

            alert(`Service removed successfully.`);
            setReservation(response.data); // Update UI with new reservation state
        } catch (error) {
            console.error("Error removing service:", error);
            alert("Failed to remove service. Try again.");
        }
    };


    return (

        <div>
            <header className="w3l-header-nav">
                <nav className="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
                    <div className="container">
                        <Link className="navbar-brand" to="/">
                            <img src={hotelLogo} alt="Your logo" style={{height: '35px'}}/> Hotels
                        </Link>
                        <button className="navbar-toggler collapsed" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span className="fa icon-expand fa-bars"></span>
                            <span className="fa icon-close fa-times"></span>
                        </button>

                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav ml-auto">
                                <li className="nav-item">
                                    <Link className="nav-link" to="/">Home</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/services">Services</Link>
                                </li>
                                <li className="nav-item ">
                                    <Link className="nav-link" to="/rooms">Rooms</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/myreservation">MyReservation</Link>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="contact.html">Contact</a>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/login">Login</Link>
                                </li>
                            </ul>
                            <Link className="ml-3 book btn btn-secondary btn-style" to="/rooms" >Book Now</Link>
                        </div>
                    </div>
                </nav>
            </header>


            <div style={{padding: "20px"}}>
                <h2>My Reservation</h2>

                <div className="d-flex justify-content-start mb-4">
                    <div className="card shadow-sm" style={{minWidth: '260px'}}>
                        <div className="card-body py-3">
                            <h6 className="card-title mb-1 text-muted">Total Price</h6>
                            <h4 className="fw-bold mb-0">€{resData.totalPrice}</h4>
                        </div>
                    </div>
                </div>

                <hr/>
                <div className="row d-flex align-items-stretch mb-4">
                    {/* Reservation Details */}
                    <div className="col-md-6 mb-3">
                        <div className="card shadow-sm p-3 bg-white rounded h-100">
                            <h4 className="card-title">Reservation Details</h4>
                            <div className="card-body p-0">
                                <p><strong>Check-in:</strong> {resData.checkIn}</p>
                                <p><strong>Check-out:</strong> {resData.checkOut}</p>
                                <p><strong>Status:</strong> {resData.reservationStatus}</p>
                                <p><strong>Guests:</strong> {room.maxOccupancy}</p>
                            </div>
                        </div>
                    </div>

                    {/* Room Details */}
                    <div className="col-md-6 mb-3">
                        <div className="card shadow-sm p-3 bg-white rounded h-100">
                            <h4 className="card-title">Room Details</h4>
                            <div className="card-body p-0">
                                <p><strong>Room Type:</strong> {room.roomType}</p>
                                <p><strong>Description:</strong> {room.description}</p>
                                <p><strong>Facilities:</strong> Balcony, Mini Fridge, AC, TV, Wi-Fi</p>
                                <p><strong>Price per Night:</strong> €{room.pricePerNight}</p>
                            </div>
                        </div>
                    </div>
                </div>

                <h3>Added Services</h3>
                {hotelServices.length === 0 ? (
                    <p>No services added to your reservation.</p>
                ) : (
                    <table className="table table-bordered">
                        <thead>
                        <tr>
                            <th>Service</th>
                            <th>Description</th>
                            <th>Price (€)</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        {hotelServices.map((service) => (
                            <tr key={service.serviceId}>
                                <td>{service.serviceName}</td>
                                <td>{service.description}</td>
                                <td>{service.price}</td>
                                <td>
                                    <button
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleRemoveService(service.serviceId)}
                                    >
                                        Remove
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}

            </div>

            {/*Footer*/}
            <section className="w3l-footer-29-main">
                <div className="footer-29 py-5">
                    <div className="container py-lg-4">
                        <div className="row footer-top-29">
                            <div className="col-lg-3 col-md-6 col-sm-8 footer-list-29 footer-1">
                                <h6 className="footer-title-29">Contact Us</h6>
                                <ul>
                                    <li>
                                        <p><span className="fa fa-map-marker"></span> Luxury hotel, #32841 block,
                                            #221DRS Rental & Paid rooms
                                            business, UK.</p>
                                    </li>
                                    <li><a href="tel:+7-800-999-800"><span
                                        className="fa fa-phone"></span> +(21)-255-999-8888</a></li>
                                    <li><a href="mailto:hotels@mail.com" className="mail"><span
                                        className="fa fa-envelope-open-o"></span>
                                        hotels@mail.com</a></li>
                                </ul>
                            </div>
                            <div className="col-lg-2 col-md-6 col-sm-4 footer-list-29 footer-2 mt-sm-0 mt-5">

                                <ul>
                                    <h6 className="footer-title-29">Useful Links</h6>
                                    <li><a href="index.html">Home</a></li>
                                    <li><a href="about.html">About hotels</a></li>
                                    <li><a href="blog.html"> Blog posts</a></li>
                                    <li><a href="contact.html">Contact us</a></li>
                                </ul>
                            </div>
                            <div className="col-lg-3 col-md-6 col-sm-5 footer-list-29 footer-3 mt-lg-0 mt-5">
                                <h6 className="footer-title-29">Latest from blog</h6>
                                <div className="footer-post mb-4">
                                    <a href="blog-single.html">Work Passionately</a>
                                    <p className="small"><span className="fa fa-clock-o"></span> March 9, 2020</p>
                                </div>
                                <div className="footer-post">
                                    <a href="blog-single.html">Work Passionately without any hesitation</a>
                                    <p className="small"><span className="fa fa-clock-o"></span> March 9, 2020</p>
                                </div>

                            </div>
                            <div className="col-lg-4 col-md-6 col-sm-7 footer-list-29 footer-4 mt-lg-0 mt-5">
                                <h6 className="footer-title-29">Newsletter </h6>
                                <p>Enter your email and receive the latest news from us.
                                    We'll never share your email address</p>

                                <form action="#" className="subscribe" method="post">
                                    <input type="email" name="email" placeholder="Your Email Address" required=""/>
                                    <button><span className="fa fa-envelope-o"></span></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>

    )
        ;
};

export default MyReservation;
