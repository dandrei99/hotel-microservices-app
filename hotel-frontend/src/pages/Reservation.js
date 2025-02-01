import React, {useState} from "react";
import App from "../App";
import {Link, useParams} from "react-router-dom";
import hotelLogo from "../assets/images/hotels.png";

const Reservation = () => {

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
                                <li className="nav-item @@home__active">
                                    <Link className="nav-link" to="/">Home</Link>
                                </li>
                                <li className="nav-item @@about__active">
                                    <a className="nav-link" href="about.html">About</a>
                                </li>
                                <li className="nav-item active">
                                    <Link className="nav-link" to="/services">Services</Link>
                                </li>
                                <li className="nav-item dropdown @@room__active">
                                    <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Rooms <span className="fa fa-angle-down"></span>
                                    </a>
                                    <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <Link className="dropdown-item" to="/rooms">Rooms</Link>
                                        <a className="dropdown-item" href="room-single.html">Room Single</a>
                                    </div>
                                </li>
                                <li className="nav-item dropdown @@blog__active">
                                    <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Blog <span className="fa fa-angle-down"></span>
                                    </a>
                                    <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a className="dropdown-item" href="blog.html">Blog</a>
                                        <a className="dropdown-item" href="blog-single.html">Blog Single</a>
                                    </div>
                                </li>
                                <li className="nav-item @@contact__active">
                                    <a className="nav-link" href="contact.html">Contact</a>
                                </li>
                            </ul>
                            <a href="booking.html" className="ml-3 book btn btn-secondary btn-style">Book Now</a>
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
                                <p>Lorem ipsum dolor sit amet,Ea consequuntur illum facere aperiam sequi optio
                                    consectetur
                                    adipisicing elit Ea consequuntur illum.</p>
                            </div>
                            <div className="form-right-inf">
                                <div className="booking-form-content">
                                    <h6>Book Now</h6>
                                    <form action="#" className="book-depature-6 signin-form" method="post">
                                        <div className="d-grid grid-col-2">
                                            {/*<div className="hny-frm_grid">*/}
                                            {/*    <h5>Name</h5>*/}
                                            {/*    <input className="name" name="Text" type="text" placeholder="Your Name"*/}
                                            {/*           required=""/>*/}
                                            {/*</div>*/}
                                            {/*<div className="hny-frm_grid">*/}
                                            {/*    <h5>Phone Number</h5>*/}
                                            {/*    <input className="phone" name="Text" type="phone"*/}
                                            {/*           placeholder="Phone Number" required=""/>*/}
                                            {/*</div>*/}
                                            <div className="hny-frm_grid">
                                                <h5>Check-in Date</h5>
                                                <input
                                                    className="date"
                                                    name="checkIn"
                                                    type="date"
                                                    placeholder="Date"
                                                    required
                                                    // value = {reservationDates.checkIn}
                                                    // onChange={handleChange}
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
                                                    // value = {reservationDates.checkOut}
                                                    // onChange={handleChange}
                                                />
                                            </div>
                                        {/*</div>*/}
                                        {/*<div className="d-grid grid-col-2 mt-3">*/}
                                        {/*    <div className="hny-frm_grid">*/}
                                        {/*        <h5>Adults</h5>*/}
                                        {/*        <select id="category1" name="category1" required="">*/}
                                        {/*            <option value="category1">01</option>*/}
                                        {/*            <option value="category2">02</option>*/}
                                        {/*            <option value="category3">03</option>*/}
                                        {/*            <option value="category4">04</option>*/}
                                        {/*            <option value="category2">05</option>*/}
                                        {/*            <option value="category3">06</option>*/}
                                        {/*        </select>*/}
                                        {/*    </div>*/}
                                        {/*    <div className="hny-frm_grid">*/}
                                        {/*        <h5>Children</h5>*/}
                                        {/*        <select id="category1" name="category1" required="">*/}
                                        {/*            <option value="category1">01</option>*/}
                                        {/*            <option value="category2">02</option>*/}
                                        {/*            <option value="category3">03</option>*/}
                                        {/*            <option value="category4">04</option>*/}
                                        {/*            <option value="category2">05</option>*/}
                                        {/*            <option value="category3">06</option>*/}
                                        {/*        </select>*/}
                                        {/*    </div>*/}
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