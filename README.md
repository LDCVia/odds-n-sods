odds-n-sods
=========

As the name suggests, various snippets of code that may be useful here and there. As you add code, please list the key bits below:

### NodeCryptoTest
This Java class is written to play nicely with the accompanying node.js app. Encrypt a token in the node app, then feed the same key, IV and the encrypted string through the `NodeCryptoTest` class, and hopefully the latter wil deccrypt it (you may need a Java policy tweak to avoid illegal key length errors).

(To fire up the node app, simply navigate into the node directory and issue this command: `node app.js`).

Tickety boo.
