// server.js (full)
require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');

console.log("MONGO_URI =", process.env.MONGO_URI);

const app = express();
app.use(express.json());
app.use(cors());

// connect to MongoDB
mongoose.connect(process.env.MONGO_URI)
  .then(() => console.log("✅ MongoDB Connected"))
  .catch(err => console.error("❌ MongoDB Connection Error:", err));

// Donor model (you already had this)
const donorSchema = new mongoose.Schema({
  name: String,
  bloodGroup: String,
  phone: String
});
const Donor = mongoose.model('Donor', donorSchema);

// User model (signup/login)
const userSchema = new mongoose.Schema({
  name: String,
  email: { type: String, unique: true },
  password: String,
  phone: String
});
const User = mongoose.model('User', userSchema);

// Optional: simple request logger (helps debugging)
app.use((req, res, next) => {
  console.log(new Date().toISOString(), req.method, req.url, req.body);
  next();
});

// donor routes (already)
app.post('/add-donor', async (req, res) => {
  try {
    const newDonor = new Donor(req.body);
    await newDonor.save();
    res.status(201).json({ message: 'Donor added!' });
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});
app.get('/donors', async (req, res) => {
  const donors = await Donor.find();
  res.json(donors);
});

// signup route
app.post('/signup', async (req, res) => {
  try {
    const { name, email, password, phone } = req.body;
    if (!name || !email || !password) return res.status(400).json({ message: 'All fields required' });
    const exists = await User.findOne({ email });
    if (exists) return res.status(400).json({ message: 'Email already exists' });
    const newUser = new User({ name, email, password, phone });
    await newUser.save();
    res.status(201).json({ message: 'Signup successful' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// login route
app.post('/login', async (req, res) => {
  try {
    const { email, password } = req.body;
    if (!email || !password) return res.status(400).json({ message: 'Email and password required' });
    const user = await User.findOne({ email });
    if (!user || user.password !== password) return res.status(401).json({ message: 'Invalid email or password' });
    // for testing return small user object; in production return JWT
    res.json({ message: 'Login successful', user: { id: user._id, name: user.name, email: user.email } });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// start server
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => console.log(`🚀 Server running on port ${PORT}`));
