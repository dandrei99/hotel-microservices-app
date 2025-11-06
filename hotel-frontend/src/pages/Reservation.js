import React, {useState} from "react";
import {Link, useParams} from "react-router-dom";
import hotelLogo from "../assets/images/hotels.png";
import axios from "axios";
import {toast} from "react-toastify";

const Reservation = () => {
    const {roomId} = useParams(); // Get roomId from the URL

    const [reservationDates, setReservationDates] = useState({
        checkIn: '',
        checkOut: ''
    });

    const handleChange = (e) => {
        setReservationDates({
            ...reservationDates,
            [e.target.name]: e.target.value
        });
    };

    const formatDate = (dateString) => {
        const [year, month, day] = dateString.split('-'); // Split the ISO date
        return `${day}/${month}/${year}`; // Convert to DD/MM/YYYY
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const token = localStorage.getItem('jwtToken'); // Get JWT from localStorage

            // Format the dates before sending to the backend
            const formattedCheckIn = formatDate(reservationDates.checkIn);
            const formattedCheckOut = formatDate(reservationDates.checkOut);

            const response = await axios.post(
                `/api-gateway/api/reservations?roomId=${roomId}`
                ,
                {
                    checkIn: formattedCheckIn,
                    checkOut: formattedCheckOut
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        'Content-Type': 'application/json'
                    }
                }
            );

            console.log('Reservation successful:', response.data);
            toast.info('Reservation successfully created!');
        }catch(error) {
            console.error('Error creating reservation:', error);
            toast.error('Error creating reservation. Please try again.');
        }
    };

    return (
        <div>

            {/*Header*/}
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
                            <Link className="ml-3 book btn btn-secondary btn-style" to="/rooms">Book Now</Link>
                        </div>
                    </div>
                </nav>
            </header>

            {/*Form*/}
            <section className="w3l-booking-top">
                <div className="booking-form-61">
                    <div className="container">
                        <div className="booking-top-gds">
                            <div className="booking-forms-16-info align-self">
                                <h5>Your Reservation</h5>
                                <h3 className="title-big">Select the Room, check for availability and book it.</h3>
                            </div>
                            <div className="form-right-inf">
                                <div className="booking-form-content">
                                    <h6>Book Now</h6>
                                    <form onSubmit={handleSubmit} className="book-depature-6 signin-form" method="post">
                                        <div className="d-grid grid-col-2">
                                            {/*</div>*/}
                                            <div className="hny-frm_grid">
                                                <h5>Check-in Date</h5>
                                                <input
                                                    className="date"
                                                    name="checkIn"
                                                    type="date"
                                                    placeholder="Date"
                                                    required
                                                    value={reservationDates.checkIn}
                                                    onChange={handleChange}
                                                />
                                            </div>
                                            <div className="hny-frm_grid">
                                                <h5>Check-out Date</h5>
                                                <input
                                                    className="date"
                                                    name="checkOut"
                                                    type="date"
                                                    placeholder="Date"
                                                    required
                                                    value={reservationDates.checkOut}
                                                    onChange={handleChange}
                                                />
                                            </div>
                                        </div>
                                        <button className="btn btn-style btn-secondary book mt-3">Book Now</button>
                                        <p className="already">You are booking as guest.</p>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>


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
};

export default Reservation;