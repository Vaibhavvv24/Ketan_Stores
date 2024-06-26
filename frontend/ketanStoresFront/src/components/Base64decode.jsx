import React, { useEffect, useState } from "react";

const Base64decode = ({ base64String }) => {
  const [imageUrl, setImageUrl] = useState(null);
  useEffect(() => {
    const convertBase64ToImage = () => {
      const binaryString = window.atob(base64String);
      const byteArray = new Uint8Array(binaryString.length);
      for (let i = 0; i < binaryString.length; i++) {
        byteArray[i] = binaryString.charCodeAt(i);
      }

      const blob = new Blob([byteArray], { type: "image/jpeg" });

      const url = URL.createObjectURL(blob);

      setImageUrl(url);
    };

    convertBase64ToImage();

    // Cleanup function
    return () => {
      if (imageUrl) {
        URL.revokeObjectURL(imageUrl);
      }
    };
  }, [base64String]);
  return (
    <div>
      {imageUrl && (
        <img
          src={imageUrl}
          alt="Decoded Image"
          className="object-cover"
          style={ { width: "100%", height: "100%" } }
        />
      )}
    </div>
  );
};

export default Base64decode;