public abstract class Cipher implements CipherControl {

  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {
    return null;
  }

  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {
    return null;
  }
}

class Atbash extends Cipher implements CipherControl {

  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphaLower = "abcdefghijklmnopqrstuvwxyz";

    int strLength = plainString.length();
    int alphLength = alphabet.length();
    StringBuilder cipher = new StringBuilder("");

    for (int i = 0; i < strLength; i++) {
      char b = plainString.charAt(i);
      if (b == ' ') {
        cipher.append(" ");
      }

      for (int j = 0; j < alphLength; j++) {
        char c = alphabet.charAt(j);
        char d = alphaLower.charAt(j);

        if (c == b) {
          int index = alphabet.indexOf(c);
          int position = (alphLength - 1) - index;
          cipher.append(alphabet.charAt(position));

        } else if (d == b) {
          int index = alphabet.indexOf(c);
          int position = (alphLength - 1) - index;
          cipher.append(alphaLower.charAt(position));
        }

      }

    }

    return cipher;

  }

  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphaLower = "abcdefghijklmnopqrstuvwxyz";

    int strLength = encryptedString.length();
    int alphLength = alphabet.length();
    StringBuilder decipher = new StringBuilder("");

    for (int i = 0; i < strLength; i++) {
      char b = encryptedString.charAt(i);
      if (b == ' ') {
        decipher.append(" ");
      }

      for (int j = 0; j < alphLength; j++) {
        char c = alphabet.charAt(j);
        char d = alphaLower.charAt(j);

        if (c == b) {
          int index = alphabet.indexOf(c);
          int position = (alphLength - 1) - index;
          decipher.append(alphabet.charAt(position));

        } else if (d == b) {
          int index = alphabet.indexOf(c);
          int position = (alphLength - 1) - index;
          decipher.append(alphaLower.charAt(position));
        }

      }

    }
    return decipher;

  }

}

class Caesar extends Cipher implements CipherControl {

  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {
    // StringBuilder to hold encrypted String
    StringBuilder encryptedString = new StringBuilder();

    // loop adding each ASCII character by the offset provided
    for (int i = 0; i < plainString.length(); i++) {
      char letter = plainString.charAt(i);
      int letterInt = letter + offset;

      encryptedString.append((char) letterInt);
    }

    return encryptedString;
  }

  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {

    // StringBuilder to hold encrypted String
    StringBuilder decryptedString = new StringBuilder();

    // loop adding each ASCII character by the offset provided
    for (int i = 0; i < encryptedString.length(); i++) {
      char letter = encryptedString.charAt(i);
      int letterInt = letter - offset;

      decryptedString.append((char) letterInt);
    }

    return decryptedString;

  }
}

class Vigenere extends Cipher implements CipherControl {

  @Override
  public StringBuilder encrypt(String phrase, int offset, String key) {

    StringBuilder newString = new StringBuilder();                //Final encrypted string
    int passChar;                         //Pass index value
    int passIndex = -1;                    //Pass array value
    int divisibility = 1;                 //How many times the alphabet repeats

    //System.out.println("Encryption length = " + phraseEncrypter.length());

    for (int i = 0; i < phrase.length(); i++) {
      char tempChar = (char) (phrase.charAt(i) - 96);   //phrase index character
      char newChar;                       //Temp character

      if (passIndex >= key.length() - 1) //Does encrypted password index need to be looped?
      {
        passIndex = 0;
      } else {
        passIndex++;
      }

      System.out.println("Encryption index = " + passIndex);
      passChar =
          (int) key.charAt(passIndex) - 96;               //Encrypted char = Encrypted char at index

      if (phrase.charAt(i)
          != ' ') {                                            //If phrase character is not a space,
        System.out.println(
            (char) (tempChar + 96) + "," + (int) tempChar + " + " + (char) ((int) passChar + 96)
                + "," + passChar + " = " + (char) ((int) tempChar + passChar + 96) + "," + (
                (int) tempChar + passChar));
        if ((tempChar + passChar)
            > 25) {                              //If phrase char and encryption char are over alphabet length,
          divisibility = ((tempChar + passChar)
              / 26);                  //How many times does the alphabet repeats
          System.out.println(
              divisibility + " = divisible");              //How many times the alphabet is repeated
          newChar = (char) Math.abs((tempChar + passChar) - (25 * divisibility)
              + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
        } else {                                                         //Else if phrase is within alphabet range,
          newChar = (char) (tempChar + passChar + 96);
        }
      } else {
        newChar = ' ';
      }

      newString.append(newChar);

      //System.out.println(newChar);
    }
    return newString;
  }

  @Override
  public StringBuilder decrypt(String encryption, int offset, String key) {

    StringBuilder newString = new StringBuilder();   //Final encrypted string
    int passChar;                         //Pass index value
    int passIndex = -1;                    //Pass array value
    int divisibility = 1;                 //How many times the alphabet repeats

    //System.out.println("Encryption length = " + phraseEncrypter.length());

    for (int i = 0; i < encryption.length(); i++) {
      char tempChar = (char) (encryption.charAt(i) - 96);   //phrase index character
      char newChar;                       //Temp character

      if (passIndex >= key.length() - 1) //Does encrypted password index need to be looped?
      {
        passIndex = 0;
      } else {
        passIndex++;
      }

      System.out.println("Encryption index = " + passIndex);
      passChar =
          (int) key.charAt(passIndex) - 96;               //Encrypted char = Encrypted char at index

      if (encryption.charAt(i)
          != ' ') {                                            //If phrase character is not a space,
        System.out.println(
            (char) (tempChar + 96) + "," + (int) tempChar + " - " + (char) ((int) passChar + 96)
                + "," + passChar + " = " + (char) ((int) tempChar - passChar + 96) + "," + (
                (int) tempChar - passChar));
        if ((tempChar - passChar) > 0 && (tempChar - passChar)
            < 25) {                              //If phrase char and encryption char are over alphabet length,
          newChar = (char) (tempChar - passChar + 96);
          //divisibility = ((tempChar - passChar) / -26);                  //How many times does the alphabet repeats
          //System.out.println(divisibility + " = divisible");              //How many times the alphabet is repeated
          //newChar = (char) Math.abs((tempChar - passChar) - (25 * divisibility) + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
        } else {                                                         //Else if phrase is within alphabet range,
          if ((tempChar - passChar) < 0 && (tempChar - passChar) > -25) {
            newChar = (char) Math.abs(25 + (tempChar - passChar)
                + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
          } else if ((tempChar - passChar) < -26) {
            divisibility = ((tempChar - passChar)
                / -26);                  //How many times does the alphabet repeats
            System.out.println(divisibility
                + " = divisible");              //How many times the alphabet is repeated
            newChar = (char) Math.abs((tempChar - passChar) - (25 * divisibility)
                + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
          } else {
            newChar = '*';
          }
        }
      } else {
        newChar = ' ';
      }

      newString.append(newChar);

      //System.out.println(newChar);
    }
    return newString;
  }

}



