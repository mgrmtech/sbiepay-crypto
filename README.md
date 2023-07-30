# SBIEPay Crypto Utility

This project provides a command-line utility for encryption and decryption using AES256 CBC mode, meant for cryptographic operations needed for integration with SBIePay. The utility is packaged as a standalone Java Archive (JAR) file. It can be used to generate encryption keys, encrypt text, and decrypt encrypted text.

## Getting Started

First, download the latest release of the JAR file from the [releases page](https://github.com/mgrmtech/sbiepay-crypto/releases/tag/1.0.0).

You'll need to have Java Runtime Environment (JRE) version 1.8 or higher installed on your machine.

## Usage

Open your command line interface and navigate to the directory containing the downloaded JAR file.

To encrypt a piece of text, use the `encrypt` command followed by the text to encrypt and the encryption key:

```
java -jar sbiepay-crypto.jar encrypt "Your Text Here" "YourKeyHere"
```

To decrypt encrypted text, use the `decrypt` command followed by the text to decrypt and the decryption key:

```
java -jar sbiepay-crypto.jar decrypt "EncryptedTextHere" "YourKeyHere"
```

To generate a new encryption key, use the `generateNewKey` command:

```
java -jar sbiepay-crypto.jar generateNewKey
```

## License

This project is licensed under the terms of the MIT license.
