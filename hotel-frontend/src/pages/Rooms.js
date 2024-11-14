import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import axios from "axios";

import hotelLogo from "../assets/images/hotels.png";
import about1 from "../assets/images/about1.jpg";
import about3 from "../assets/images/about3.jpg";
import blog3 from "../assets/images/blog3.jpg";
import blog1 from "../assets/images/blog1.jpg";
import blog2 from "../assets/images/blog2.jpg";
import about2 from "../assets/images/about2.jpg";

const Rooms = () => {
    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:9191/api/rooms/all')
            .then((response) => {
                console.log(response.data);
                setRooms(response.data); //set fetched data
            })
            .catch((error) => {
                console.error('Error fetching rooms:', error);
            })
    }, []);

    //declared array of pictures to use them when iterating below, in the Rooms Section, one for each room
    const roomImages = [blog1, about3, blog3, blog2, about1, about2];

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

            {/*Breadcrumb*/}
            <section className="w3l-breadcrumb">
                <div className="breadcrum-bg py-sm-5 py-4">
                    <div className="container py-lg-3">

                        <h2>Rooms</h2>
                        <p><a href="index.html">Home</a> &nbsp; / &nbsp; rooms</p>

                    </div>
                </div>
            </section>

            {/*Rooms Section*/}
            <div className="best-rooms w3l-blog py-5">
                <div className="container py-lg-5 py-sm-4">
                    <div className="ban-content-inf row">

                        {rooms.map((room, index) => (
                            <div className="maghny-gd-1 col-lg-4 col-md-6" key={room.roomId}>
                                <div className="maghny-grid">
                                    <figure className="effect-lily">
                                        <img className="img-fluid" src={roomImages[index]} alt={`Room ${index + 1}`}/>
                                        <figcaption>
                                            <div>
                                                <h4 className="top-text">room nr.{room.roomId}
                                                    <ul>
                                                        <li><span className="fa fa-star"></span></li>
                                                        <li><span className="fa fa-star"></span></li>
                                                        <li><span className="fa fa-star"></span></li>
                                                        <li><span className="fa fa-star"></span></li>
                                                        <li><span className="fa fa-star-o"></span></li>
                                                    </ul>
                                                </h4>
                                                <p>Book for {room.pricePerNight}$ </p>
                                            </div>
                                        </figcaption>
                                    </figure>
                                    <div className="room-info">
                                        <h3 className="room-title"><a href="#url">Luxury Hotel</a></h3>
                                        <ul className="mb-3">
                                            <li><span className="fa fa-users"></span> {room.maxOccupancy} Guests</li>
                                            <li><span className="fa fa-bed"></span> {room.surface} sqft</li>
                                        </ul>
                                        <p>{room.description}</p>
                                        <a href="booking.html" className="btn mt-sm-4 mt-3">Book Now</a>
                                        <div className="room-info-bottom">
                                            <ul className="room-amenities">
                                                <li><a href="#url"><span className="fa fa-bed" title="Beds"></span></a>
                                                </li>
                                                <li><a href="#url"><span className="fa fa-television"
                                                                         title="Television"></span></a></li>
                                                <li><a href="#url"><span className="fa fa-bath"
                                                                         title="Private Bathroom"></span></a></li>
                                                <li><a href="#url"><span className="fa fa-motorcycle"
                                                                         title="Bike Rental"></span></a></li>
                                            </ul>
                                            <a href="room-single.html" className="btn view">Full Info →</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}


                        {/*            <div className="maghny-gd-1 col-lg-4 col-md-6">*/}
                        {/*                <div className="maghny-grid">*/}
                        {/*                    <figure className="effect-lily">*/}
                        {/*                        <img className="img-fluid" src={about1} alt=""/>*/}
                        {/*                        <figcaption>*/}
                        {/*                            <div>*/}
                        {/*                                <h4 className="top-text">Luxury Hotel room*/}
                        {/*                                    <ul>*/}
                        {/*                                        <li><span className="fa fa-star"></span></li>*/}
                        {/*                                        <li><span className="fa fa-star"></span></li>*/}
                        {/*                                        <li><span className="fa fa-star"></span></li>*/}
                        {/*                                        <li><span className="fa fa-star"></span></li>*/}
                        {/*                                        <li><span className="fa fa-star-o"></span></li>*/}
                        {/*                                    </ul>*/}
                        {/*                                </h4>*/}
                        {/*                                <p>Book for 20$ </p>*/}
                        {/*                            </div>*/}
                        {/*                        </figcaption>*/}
                        {/*                    </figure>*/}
                        {/*                    <div className="room-info">*/}
                        {/*                        <h3 className="room-title"><a href="#url">Luxury Hotel</a></h3>*/}
                        {/*                        <ul className="mb-3">*/}
                        {/*                            <li><span className="fa fa-users"></span> 2 Guests</li>*/}
                        {/*                            <li><span className="fa fa-bed"></span> 15sqft</li>*/}
                        {/*                        </ul>*/}
                        {/*                        <p>Lorem ipsum dolor, sit amet elit. Omnis illum sequi, tenetur.</p>*/}
                        {/*                        <a href="booking.html" className="btn mt-sm-4 mt-3">Book Now</a>*/}
                        {/*                        <div className="room-info-bottom">*/}
                        {/*                            <ul className="room-amenities">*/}
                        {/*                                <li><a href="#url"><span className="fa fa-bed" title="Beds"></span></a></li>*/}
                        {/*                                <li><a href="#url"><span className="fa fa-television"*/}
                        {/*                                                         title="Television"></span></a></li>*/}
                        {/*                                <li><a href="#url"><span className="fa fa-bath"*/}
                        {/*                                                         title="Private Bathroom"></span></a></li>*/}
                        {/*                                <li><a href="#url"><span className="fa fa-motorcycle"*/}
                        {/*                                                         title="Bike Rental"></span></a></li>*/}
                        {/*                            </ul>*/}
            {/*                            <a href="room-single.html" className="btn view">Full Info →</a>*/}
            {/*                        </div>*/}
            {/*                    </div>*/}
            {/*                </div>*/}
            {/*            </div>*/}
            {/*            <div className="maghny-gd-1 col-lg-4 col-md-6 mt-md-0 mt-4">*/}
            {/*                <div className="maghny-grid">*/}
            {/*                    <figure className="effect-lily">*/}
            {/*                        <img className="img-fluid" src={blog3} alt=""/>*/}
            {/*                        <figcaption>*/}
            {/*                            <div>*/}
            {/*                                <h4 className="top-text">Luxury Hotel room*/}
            {/*                                    <ul>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star-o"></span></li>*/}
            {/*                                    </ul>*/}
            {/*                                </h4>*/}
            {/*                                <p>Book for 20$ </p>*/}
            {/*                            </div>*/}
            {/*                        </figcaption>*/}
            {/*                    </figure>*/}
            {/*                    <div className="room-info">*/}
            {/*                        <h3 className="room-title"><a href="#url">Luxury Hotel</a></h3>*/}
            {/*                        <ul className="mb-3">*/}
            {/*                            <li><span className="fa fa-users"></span> 2 Guests</li>*/}
            {/*                            <li><span className="fa fa-bed"></span> 15sqft</li>*/}
            {/*                        </ul>*/}
            {/*                        <p>Lorem ipsum dolor, sit amet elit. Omnis illum sequi, tenetur.</p>*/}
            {/*                        <a href="booking.html" className="btn mt-sm-4 mt-3">Book Now</a>*/}
            {/*                        <div className="room-info-bottom">*/}
            {/*                            <ul className="room-amenities">*/}
            {/*                                <li><a href="#url"><span className="fa fa-bed" title="Beds"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-television"*/}
            {/*                                                         title="Television"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-bath"*/}
            {/*                                                         title="Private Bathroom"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-motorcycle"*/}
            {/*                                                         title="Bike Rental"></span></a></li>*/}
            {/*                            </ul>*/}
            {/*                            <a href="room-single.html" className="btn view">Full Info →</a>*/}
            {/*                        </div>*/}
            {/*                    </div>*/}
            {/*                </div>*/}
            {/*            </div>*/}
            {/*            <div className="maghny-gd-1 col-lg-4 col-md-6 mt-md-0 mt-4">*/}
            {/*                <div className="maghny-grid">*/}
            {/*                    <figure className="effect-lily">*/}
            {/*                        <img className="img-fluid" src={about3} alt=""/>*/}
            {/*                        <figcaption>*/}
            {/*                            <div>*/}
            {/*                                <h4 className="top-text">Luxury Hotel room*/}
            {/*                                    <ul>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star-o"></span></li>*/}
            {/*                                    </ul>*/}
            {/*                                </h4>*/}
            {/*                                <p>Book for 20$ </p>*/}
            {/*                            </div>*/}
            {/*                        </figcaption>*/}
            {/*                    </figure>*/}
            {/*                    <div className="room-info">*/}
            {/*                        <h3 className="room-title"><a href="#url">Luxury Hotel</a></h3>*/}
            {/*                        <ul className="mb-3">*/}
            {/*                            <li><span className="fa fa-users"></span> 2 Guests</li>*/}
            {/*                            <li><span className="fa fa-bed"></span> 15sqft</li>*/}
            {/*                        </ul>*/}
            {/*                        <p>Lorem ipsum dolor, sit amet elit. Omnis illum sequi, tenetur.</p>*/}
            {/*                        <a href="booking.html" className="btn mt-sm-4 mt-3">Book Now</a>*/}
            {/*                        <div className="room-info-bottom">*/}
            {/*                            <ul className="room-amenities">*/}
            {/*                                <li><a href="#url"><span className="fa fa-bed" title="Beds"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-television"*/}
            {/*                                                         title="Television"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-bath"*/}
            {/*                                                         title="Private Bathroom"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-motorcycle"*/}
            {/*                                                         title="Bike Rental"></span></a></li>*/}
            {/*                            </ul>*/}
            {/*                            <a href="room-single.html" className="btn view">Full Info →</a>*/}
            {/*                        </div>*/}
            {/*                    </div>*/}
            {/*                </div>*/}
            {/*            </div>*/}
            {/*            <div className="maghny-gd-1 col-lg-4 col-md-6 mt-md-5 mt-4">*/}
            {/*                <div className="maghny-grid">*/}
            {/*                    <figure className="effect-lily">*/}
            {/*                        <img className="img-fluid" src={blog1} alt=""/>*/}
            {/*                        <figcaption>*/}
            {/*                            <div>*/}
            {/*                                <h4 className="top-text">Luxury Hotel room*/}
            {/*                                    <ul>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star-o"></span></li>*/}
            {/*                                    </ul>*/}
            {/*                                </h4>*/}
            {/*                                <p>Book for 20$ </p>*/}
            {/*                            </div>*/}
            {/*                        </figcaption>*/}
            {/*                    </figure>*/}
            {/*                    <div className="room-info">*/}
            {/*                        <h3 className="room-title"><a href="#url">Luxury Hotel</a></h3>*/}
            {/*                        <ul className="mb-3">*/}
            {/*                            <li><span className="fa fa-users"></span> 2 Guests</li>*/}
            {/*                            <li><span className="fa fa-bed"></span> 15sqft</li>*/}
            {/*                        </ul>*/}
            {/*                        <p>Lorem ipsum dolor, sit amet elit. Omnis illum sequi, tenetur.</p>*/}
            {/*                        <a href="booking.html" className="btn mt-sm-4 mt-3">Book Now</a>*/}
            {/*                        <div className="room-info-bottom">*/}
            {/*                            <ul className="room-amenities">*/}
            {/*                                <li><a href="#url"><span className="fa fa-bed" title="Beds"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-television"*/}
            {/*                                                         title="Television"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-bath"*/}
            {/*                                                         title="Private Bathroom"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-motorcycle"*/}
            {/*                                                         title="Bike Rental"></span></a></li>*/}
            {/*                            </ul>*/}
            {/*                            <a href="room-single.html" className="btn view">Full Info →</a>*/}
            {/*                        </div>*/}
            {/*                    </div>*/}
            {/*                </div>*/}
            {/*            </div>*/}
            {/*            <div className="maghny-gd-1 col-lg-4 col-md-6 mt-5">*/}
            {/*                <div className="maghny-grid">*/}
            {/*                    <figure className="effect-lily">*/}
            {/*                        <img className="img-fluid" src={blog2} alt=""/>*/}
            {/*                        <figcaption>*/}
            {/*                            <div>*/}
            {/*                                <h4 className="top-text">Luxury Hotel room*/}
            {/*                                    <ul>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star-o"></span></li>*/}
            {/*                                    </ul>*/}
            {/*                                </h4>*/}
            {/*                                <p>Book for 20$ </p>*/}
            {/*                            </div>*/}
            {/*                        </figcaption>*/}
            {/*                    </figure>*/}
            {/*                    <div className="room-info">*/}
            {/*                        <h3 className="room-title"><a href="#url">Luxury Hotel</a></h3>*/}
            {/*                        <ul className="mb-3">*/}
            {/*                            <li><span className="fa fa-users"></span> 2 Guests</li>*/}
            {/*                            <li><span className="fa fa-bed"></span> 15sqft</li>*/}
            {/*                        </ul>*/}
            {/*                        <p>Lorem ipsum dolor, sit amet elit. Omnis illum sequi, tenetur.</p>*/}
            {/*                        <a href="booking.html" className="btn mt-sm-4 mt-3">Book Now</a>*/}
            {/*                        <div className="room-info-bottom">*/}
            {/*                            <ul className="room-amenities">*/}
            {/*                                <li><a href="#url"><span className="fa fa-bed" title="Beds"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-television"*/}
            {/*                                                         title="Television"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-bath"*/}
            {/*                                                         title="Private Bathroom"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-motorcycle"*/}
            {/*                                                         title="Bike Rental"></span></a></li>*/}
            {/*                            </ul>*/}
            {/*                            <a href="room-single.html" className="btn view">Full Info →</a>*/}
            {/*                        </div>*/}
            {/*                    </div>*/}
            {/*                </div>*/}
            {/*            </div>*/}
            {/*            <div className="maghny-gd-1 col-lg-4 col-md-6 mt-5">*/}
            {/*                <div className="maghny-grid">*/}
            {/*                    <figure className="effect-lily">*/}
            {/*                        <img className="img-fluid" src={about2} alt=""/>*/}
            {/*                        <figcaption>*/}
            {/*                            <div>*/}
            {/*                                <h4 className="top-text">Luxury Hotel room*/}
            {/*                                    <ul>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star"></span></li>*/}
            {/*                                        <li><span className="fa fa-star-o"></span></li>*/}
            {/*                                    </ul>*/}
            {/*                                </h4>*/}
            {/*                                <p>Book for 20$ </p>*/}
            {/*                            </div>*/}
            {/*                        </figcaption>*/}
            {/*                    </figure>*/}
            {/*                    <div className="room-info">*/}
            {/*                        <h3 className="room-title"><a href="#url">Luxury Hotel</a></h3>*/}
            {/*                        <ul className="mb-3">*/}
            {/*                            <li><span className="fa fa-users"></span> 2 Guests</li>*/}
            {/*                            <li><span className="fa fa-bed"></span> 15sqft</li>*/}
            {/*                        </ul>*/}
            {/*                        <p>Lorem ipsum dolor, sit amet elit. Omnis illum sequi, tenetur.</p>*/}
            {/*                        <a href="booking.html" className="btn mt-sm-4 mt-3">Book Now</a>*/}
            {/*                        <div className="room-info-bottom">*/}
            {/*                            <ul className="room-amenities">*/}
            {/*                                <li><a href="#url"><span className="fa fa-bed" title="Beds"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-television"*/}
            {/*                                                         title="Television"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-bath"*/}
            {/*                                                         title="Private Bathroom"></span></a></li>*/}
            {/*                                <li><a href="#url"><span className="fa fa-motorcycle"*/}
            {/*                                                         title="Bike Rental"></span></a></li>*/}
            {/*                            </ul>*/}
            {/*                            <a href="room-single.html" className="btn view">Full Info →</a>*/}
            {/*                        </div>*/}
            {/*                    </div>*/}
            {/*                </div>*/}
            {/*            </div>*/}
                    </div>
                </div>
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
    );
};

export default Rooms;