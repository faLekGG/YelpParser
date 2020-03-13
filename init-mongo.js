db.createUser(
  {
    user: "vhalaveika",
    pwd: "123",
    roles: [
      {
        role: "readWrite",
        db: "yelp"
      }
    ],
    mechanisms:[ "SCRAM-SHA-1"]
  }
)