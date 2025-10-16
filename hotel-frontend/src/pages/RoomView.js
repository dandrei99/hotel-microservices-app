import React, {useEffect, useState} from "react";
import {Link, useParams} from "react-router-dom";
import axios from "axios";
import hotelLogo from "../assets/images/hotels.png";
import slide1 from "../assets/images/slide1.jpg";
import slide4 from "../assets/images/slide4.jpg";
import slide2 from "../assets/images/slide2.jpg";
import slide3 from "../assets/images/slide3.jpg";
import rs1 from "../assets/images/rs1.jpg";
import rs2 from "../assets/images/rs2.jpg";
import facilities from "../assets/images/facilities.jpg";

const RoomView = () => {
    const { roomId } = useParams(); // Extract roomId from URL
    const [room, setRoom] = useState(null); // Room data state

    useEffect(() =>{

        const token = localStorage.getItem('jwtToken'); // Retrieve token from local storage

        axios.get(`http://localhost:9191/api/rooms/${roomId}`,{
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then((response) => {
                console.log(response.data);
                setRoom(response.data); //set fetched data
            })
            .catch((error) => {
                console.error('Error fetching room:', error);
            });
    }, [roomId]);

    // Show a loading state until room data is fetched
    if (!room) {
        return <div>Loading room details...</div>;
    }

    const roomImages = [slide1, slide4, slide2, slide3, rs1, rs2];

    return(

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

            {/*Breadcrumb*/}
            <section className="w3l-breadcrumb">
                <div className="breadcrum-bg py-sm-5 py-4">
                    <div className="container py-lg-3">

                        <h2>Rooms</h2>
                        <p><Link to="/">Home</Link> &nbsp; / &nbsp; Room</p>

                    </div>
                </div>
            </section>

            {/*/!*Room  view*!/*/}
            <section className="room-single-block">
                <div className="content-with-slider">
                    <div className="container">
                        <div className="content-photo-1 d-grid">
                            <div className="content-photo-left text-center">
                                <h4>Luxury Hotel</h4>
                                <h6> {room.roomType} Room nr.{room.roomId}</h6>
                                <div className="border-line">
                                    <div className="bg">
                                        <span className="price">{room.pricePerNight} €</span>
                                        <p> Per night</p>
                                    </div>
                                    <div className="book-btn px-2">
                                        <Link to={`/reservation/${room.roomId}`}
                                              className="btn btn-style btn-secondary mt-3">Book Now</Link>
                                    </div>
                                </div>
                                <ul className="room-amenities">
                                    <li><a href="#url"><span className="fa fa-beer"></span> {room.surface} sqft</a></li>
                                    <li><a href="#url"><span className="fa fa-users"></span> {room.maxOccupancy} Guests</a>
                                    </li>
                                    <li><a href="#url"><span className="fa fa-bed"></span> Double Bed</a></li>
                                </ul>
                                <Link to="/rooms" class="back"> <span className="fa fa-long-arrow-left"></span> Back
                                    to
                                    all rooms</Link>
                            </div>
                            <div className="content-photo-right">
                                <div className="csslider infinity" id="slider1">
                                    <input type="radio" name="slides" checked="checked" id="slides_1"/>
                                    <input type="radio" name="slides" id="slides_2"/>
                                    <input type="radio" name="slides" id="slides_3"/>
                                    <input type="radio" name="slides" id="slides_4"/>
                                    <ul className="banner_slide_bg">
                                        {roomImages.slice(0, 4).map((image, index) => (
                                            <li key={index}>
                                                <img className="img" src={image} alt={`Room Slide ${index + 1}`}/>
                                            </li>
                                        ))}
                                    </ul>
                                    <div className="arrows">
                                        <label for="slides_1"></label>
                                        <label for="slides_2"></label>
                                        <label for="slides_3"></label>
                                        <label for="slides_4"></label>
                                    </div>
                                    <div className="navigation">
                                        <div>
                                            <label for="slides_1"></label>
                                            <label for="slides_2"></label>
                                            <label for="slides_3"></label>
                                            <label for="slides_4"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            {/*RoomView description*/}
            <section className="w3l-roomsingleblock1 py-5">
                <div className="container py-sm-4">
                    <div className="row">
                        <div className="col-lg-3 col-md-4 col-6">
                            <img src={roomImages[4]} alt="Room photo 1" className="img-fluid"/>
                        </div>
                        <div className="col-lg-3 col-md-4 col-6">
                            <img src={roomImages[5]} alt="Room photo 2" className="img-fluid"/>
                        </div>
                        <div className="col-lg-6 roomsingle mt-lg-0 mt-4">
                            <h3 className="title-small">The Room</h3>
                            <p className="roomsingle mt-3">Duis nisi sapien, elementum finibus fermentum eget, aliquet
                                leo. Mauris hendrerit vel ex.
                                Quisque vitae luctus massa. Phasellus sed aliquam leo. Vestibulum ullamcorper a massa eu
                                fringilla. Integer ultrices finibus sed nisi.
                                in convallis felis dapibus sit amet.</p>
                        </div>
                    </div>
                </div>
            </section>

            {/*Hotel features*/}
            <section className="w3l-roomsingleblock1 py-5">
                <div className="container py-sm-4">
                    <div className="row">
                        <div className="col-lg-7 roomsingle">
                            <h3 className="title-small">Amenities provided by our hotel</h3>
                            <ul className="w3l-right-book mt-4">
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Flat screen TV</a>
                                </li>
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Free
                                    high-speed WiFi</a></li>
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Private
                                    Bathroom</a></li>
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Free parking
                                    space</a></li>
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Early Check-in</a>
                                </li>
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Swimming pool</a>
                                </li>
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Air-conditioning</a>
                                </li>
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Welcome
                                    treatment</a></li>
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Barrier-free
                                    rain shower</a></li>
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Aesop bath
                                    amenities</a></li>
                                <li><a hef="#url"><span className="fa fa-check" aria-hidden="true"></span>Bike rental
                                    facility</a></li>
                            </ul>
                        </div>
                        <div className="col-lg-5 mt-lg-0 mt-4">
                            <img src={facilities} alt="Hotel Facilities" className="img-fluid"/>
                        </div>
                    </div>
                </div>
            </section>

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

export default RoomView;