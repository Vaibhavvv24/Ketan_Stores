/** @format */
import * as React from "react";
import { useColorScheme } from "@mui/joy/styles";
import Sheet from "@mui/joy/Sheet";
import CssBaseline from "@mui/joy/CssBaseline";
import Typography from "@mui/joy/Typography";
import FormControl from "@mui/joy/FormControl";
import FormLabel from "@mui/joy/FormLabel";
import Input from "@mui/joy/Input";
import Button from "@mui/joy/Button";
import Link from "@mui/joy/Link";
import { useNavigate } from "react-router-dom";
import { useGlobalContext } from "../context";

function ModeToggle() {
  const { mode, setMode } = useColorScheme();
  const [mounted, setMounted] = React.useState(false);

  // necessary for server-side rendering
  // because mode is undefined on the server
  React.useEffect(() => {
    setMounted(true);
  }, []);
  if (!mounted) {
    return <Button variant='soft'>Change mode</Button>;
  }

  return (
    <Button
      variant='soft'
      onClick={() => {
        setMode(mode === "light" ? "dark" : "light");
      }}
    >
      {mode === "light" ? "Turn dark" : "Turn light"}
    </Button>
  );
}

export default function LoginFinal() {
  const { setemail, setpassword, email, password ,setAuthToken , setjwt} =
    useGlobalContext();

  const Navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: email,
          password: password,
        }),
      });
      const data = await response.json();
      console.log(data);
      if (data.jwt) {
        // Perform actions after successful login
        // For example, store token and navigate to another page
        setAuthToken(data);
        localStorage.setItem("authToken", JSON.stringify(data));
        setjwt(data.jwt);
        // Assuming Navigate function exists to navigate to another page
        Navigate("/ketan-stores/");
      } else {
        // Handle login errors, for example, show error message
        console.error("Login failed");
      }
    } catch (error) {
      console.error("Login error:", error);
    }
  }
  return (
    <main>
      <ModeToggle />
      <CssBaseline />
      <Sheet
        sx={{
          width: 600,
          height: 500,
          display: "flex",
          flexDirection: "column",
          justifyContent: "space-evenly",
          mx: "auto", // margin left & right
          my: 4, // margin top & bottom
          py: 3, // padding top & bottom
          px: 2, // padding left & right
          gap: 2,
          borderRadius: "sm",
          boxShadow: "md",
        }}
        variant='outlined'
      >
        <div>
          <Typography level='h4' component='h1'>
            <b>Welcome!</b>
          </Typography>
          <Typography level='body-sm'>Sign in to continue.</Typography>
        </div>
        <FormControl >
          <FormLabel>Email</FormLabel>
          <Input
            // html input attribute
            name='email'
            type='email'
            placeholder='johndoe@email.com'
            onChange={(e) => setemail(e.target.value)}
          />
        </FormControl>
        <FormControl>
          <FormLabel>Password</FormLabel>
          <Input
            // html input attribute
            name='password'
            type='password'
            placeholder='password'
            onChange={(e) => setpassword(e.target.value)}
          />
        </FormControl>
        <Button sx={{ mt: 2 /* margin top */ }} onClick={handleSubmit}>
          Log in
        </Button>
      </Sheet>
    </main>
  );
}
