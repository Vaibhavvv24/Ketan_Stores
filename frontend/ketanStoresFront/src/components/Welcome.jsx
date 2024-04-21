/** @format */

import * as React from "react";
import Box from "@mui/joy/Box";
import Stack from "@mui/joy/Stack";
import Divider from "@mui/joy/Divider";
import { CssVarsProvider, extendTheme } from "@mui/joy/styles";

const customTheme = extendTheme({
  typography: {
    h1: {
      // `--joy` is the default CSS variable prefix.
      // If you have a custom prefix, you have to use it instead.
      // For more details about the custom prefix, go to https://mui.com/joy-ui/customization/using-css-variables/#custom-prefix
      background:
        "linear-gradient(-30deg, var(--joy-palette-primary-700), var(--joy-palette-primary-400))",
      // `Webkit*` properties must come later.
      WebkitBackgroundClip: "text",
      WebkitTextFillColor: "transparent",
    },
  },
});

export default function DividerText() {
  const content = (
    <CssVarsProvider theme={customTheme}>
      <Box
        sx={{
          fontSize: "100px",
          color: "linear-gradient(-30deg, var(--joy-palette-primary-700), var(--joy-palette-primary-400))",
          fontWeight: "bold",
          textAlign: "center"
        }}
      >
        {`Welcome`}
      </Box>
    </CssVarsProvider>
  );

  <CssVarsProvider theme={customTheme}>
    <Box sx={(theme) => theme.typography.h1}>This is a gradient h1</Box>
  </CssVarsProvider>;
  const content1 = (
    <Box
      sx={{
        fontSize: "120px",
        color: "black",
        fontWeight: "bold",
        textAlign: "center",
      }}
    >
      {`Ketan Stores`}
    </Box>
  );
  const content2 = (
    <Box
      sx={{
        fontSize: "80px",
        color: "black",
        fontWeight: "bold",
        textAlign: "center",
      }}
    >
      {`to`}
    </Box>
  );

  return (
    <Stack spacing={1}>
      {content}
      <Divider>{content2}</Divider>
      {content1}
    </Stack>
  );
}
