OddsNSods
=========

Various snippets of code that may be useful here and there.

### NodeCryptoTest
This Java class is written to play nicely with the accompanying node.js app. Encrypt a token in the node app, then feed the same key, IV and the encrypted string through the `NodeCryptoTest` class, and hopefully the latter wil deccrypt it (you may need a Java policy tweak to avoid illegal key length errors).

Tickety boo.
