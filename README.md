# RSCGo Client
This is simply a refactored 202 RSClassic mudclient which I've added various features to, and made various modifications to over the years.

This is the reference client which I test the RSCGo server against, and no other client is guarenteed to work with RSCGo except this one.

Any further refactoring and/or improvements to this client codebase are welcome.

To make this client, I used Alex Storky's mildly refactored 202 as a base, myself and Mark Gore added onto that over the years whilst running a handful of PK servers for the community.  I can not guarentee that the protocol is identical to Jagex's RSClassic 202, but I do know that I strived to keep it as similar as possible.  I did change the packet header, as I didn't like how frame length was encoded.  It is now simply encoded like any other unsigned short would be.  I'm seriously considering making the length a single byte for client->server packets, as nothing the client sends needs more than 7 bits of information I am pretty sure.

Cache should be held in $HOME/.SwiftPK .  I've included the cache folder on this git repository, though if you don't manually place it into $HOME/.SwiftPK, the client is likely not going to load.  I will get around to changing this at some point to handle a cache directory in $PWD, but until then simply put the cache files there manually.  Also, RSCGo is going to support something akin to JAGGRAB to download these files automatically in a future revision, but again, until then...

Hope you enjoy the project; I know it's brought me a lot of joy over the years.  I've invested a lot of time into RSCGo and, to a lesser extent, this client.  Maybe I'll launch another server wtih them at some point, it would be fun!  I hope someone else finds it useful.