# Steps for solving System design

* Features of the system
* Non-Functional Requirement
    * Available (Important for almost every system)
        * How to resume connection
    * Latency (metrics like < 500ms)
    * Consistency (level of the consistency, eventually consistent)
    * Correctness
        * How to guarantee the correctness of the data especially for payment related feature
        * Fault tolerance, snapshot or retry policy
    * Scalable (important, detailed in following steps)
    * Privacy (GDPR), Security, Monitoring
* Estimation of throughput of the system
* High level system design (basic components)
    * Main components of the system that provide features mentioned above
    * If special requirements, what would the communication protocols.
* Based on estimations, specify database choices, schemas, etc.
* Verify workflow based on features defined
* Discuss about scalability of the system.
* Check bottlenecks of the system or ensure failure checks

# Basic knowledges

## Transport Layer Protocols

### TCP Transmission control protocol

* Reliable, contains sequence numbers to enable error-check.
* Most of the case works with HTTP -> TCP/IP. HTTP, Websocket, SMTP are typical protocols built on TCP/IP
* To establish connection, 3 handshakes:
    1.  **SYN** client sends a synchronization message to server, tells server it wants to establish a connection, and synchronizes a sequence number
    2.  **SYN-ACK** server responds with an acknowledgment number (client's sequence num + 1)
    3.  **ACK** client sends an ACK message back

### UDP User datagram protocol

* TCP is expensive as it carries data along with 3-handshakes we need another protocol to handle real-time communication.
* Faster but might lose data while communicating.
* Suitable for video calls, live streaming.

#### TLS transport layer security

* **Cryptographic protocol** designed to provide secure communication over a network, the old version is SSL
* To establish connection, **TLS handshake**:
    1.  **Acknowledge** : client and server agree on the TLS version and other parameters they will use.
    2.  **Verify** : server presents its TLS certificate to the client, client checks with a trusted Certificate Authority (CA) to ensure the server is legitimate.
    3.  **Exchange** : exchange session keys (public/private keys) used for encrypting and decrypting.

## Application Layer communication protocols

### HTTP Hypertext transfer protocol

* Not secure, data transferred over plain text.
* Built on TCP/IP, handles addressing, routing, reliable delivery
* "Request-Response" model, one-way, no persistent communications, headers should carry all info
* #### **How HTTP Works:**
    -   A **client** (your browser) sends an HTTP request to a **server** for a particular resource (like a webpage).
    -   The **server** responds with the requested resource (like an HTML file or an image) or with an error message if the resource is not available.
    -   HTTP operates over a **stateless** protocol, meaning that each request is independent, and the server doesn't remember any previous requests from the client.
* Status code (optional):
    * Informational: 100 - 199
    * Successful: 200 OK, 201 created, - 299
    * Redirection: 300
    * Client error responses: 400 - 499, 400 Bad request, 401 unauthorized, 404 resource not found
    * Server error responses: 500 - 599, 500 Internal Server Error, 502 Bad Gateway, 503 Service Unavailable.

#### HTTPS

* Adds SSL/TLS on top for encryption to encrypt data.
* HTTPS uses protocols like **SSL (Secure Sockets Layer)** or more commonly **TLS (Transport Layer Security)** to encrypt data.

#### How to transform HTTP to HTTPS

-   Get an SSL/TLS certificate
-   Install the certificate on the server
-   Redirect all HTTP requests to HTTPS, also update internal calls over HTTPS
-   Test traffic
-   Setup renewal for the certificate

### RPC Remote procedure call

* Allows a program to execute a procedure in another address space as if it were a local call. Mainly used for internal communications.

### FTP File transfer protocol

* Allows file transfers over TCP, on port 21, but not encrypted.
* FTPS means FTP over SSL.

### Email related protocols

* SMTP (Simple Mail Transfer Protocol): email transmission, works over TCP for sending emails from a client to a server, or transferring emails between servers.
* POP3 (Post Office Protocol): download emails from a server to a device, by default, it will delete emails from the server after downloading.
* IMAP (Internet Message Access Protocol): retrieve emails from servers, allows synchronization across devices.

### WebSockets

* Provides a 2-way communication channel between client and server, used for **real-time** communication.
* User Auth usually with HTTPs or OAuth
* Low latency, efficiency
* Workflow
    * HTTP handshake (request to upgrade to WebSocket), server responds with a 101 status code to confirm the upgrade.
    * The connection is persistent
* For a secured version **Secure Websockets** wss://

### WebRTC web real-time communication

* Open-source technology for real-time p2p communication, directly between devices.
* Uses DTLS and SRTP for encryption
* Great for gaming, file sharing, video chat

### AMQP Advanced Message Queuing

* Queue-based, for sending messages between systems, for reliable, asynchronous and secure communication. Widely used for message brokers, e.g., RabbitMQ
* Key features:
    * **Message Queues**: messages are in a queue until they are processed
    * **Reliable**: delivered exactly 1 time, even if systems fail
    * **Asynchronous**: sender and receiver don't need to be online at the same time
    * **Acks**: receiver confirms when the message is received, the sender can retry
    * **Routing**: can route messages based on topic, rules or destinations
* Suitable for: enterprise systems that need to guarantee message delivery; Microservices for background jobs; IoT sending messages that don't need an immediate response

### MQTT Message Queuing Telemetry transport

* Lightweight, pub/sub messaging protocol for low-bandwidth, low-power networks. Ideally for IoT, e.g., Mosquitto, HiveMQ, EMQX
* Latency low: use a persistent TCP connection
* Has QoS (Quality of Service) control: 0 - at most once; 1- at least once; 2 exactly once

Choose MQTT for unreliable, low-power networks (like IoT devices sending sensor data). Choose AMQP for enterprise systems where guaranteed message delivery and complex routing between backend services are critical (like processing financial transactions).

## Throughput

* API: request per second based on daily active users, average requests per day
    * The C10M problem: concurrent connections 10M / second on a single server.
    * WhatsApp: 2 million concurrent connections back to 2012, check [1 million is so 2011](https://blog.whatsapp.com/1-million-is-so-2011)
    * Most of the case a server can handle 50k.
* DB : size of data DB needs to persist per day, or per year
* Bandwidth: bytes per second (important for chat services / files)

Cache will probably return in single-digit ms, a relational database in 30-50ms (for simple queries), a web server in 10-20ms (for simple requests) - so if you're not doing heavy processing you're probably inside that 100ms window. **Adding a component for scalability also means adding some latency**.

**Few numbers about latency**

| Action                                       | Time   | Comparison |
| :------------------------------------------- | :----- | :--------- |
| Reading 1MB sequentially from memory         | 0.25ms |            |
| Reading 1MB sequentially from SSD            | 1ms    | 4x memory  |
| Sending 1MB over network                     | 10 ms  | 10 x SSD   |
| Reading 1MB sequentially from spinning disk  | 20ms   | 20x SSD    |
| Round trip network latency CA to Netherlands | 150ms  |            |

## API design

* Versioning is important, as is the ability to paginate (limit, offset to reduce request size)
    * api.twitter.com/v1.0/tweet - POST
    * api.twitter.com/v1.0/tweet/:id - GET => v1.0/users/:id/tweets?limit=10&offset=0

### Choices

#### REST

* It's not a protocol, but a standardization of APIs, stateless, supports standard HTTP methods
* Flexible on data formats: json, xml, etc.
* Simple error handling using HTTP status codes (404, 500)

GET: api/v1/products?limit=100&offset=0

#### GraphQL

* String-typed schema-based queries, for complex data structures, mobile apps, single-page apps
* Impacts server performance
* Only have POST requests, can't cache requests
* Typically responds with **HTTP 200 OK**, even when the query results in an error. The error details are included in the JSON response body itself.

#### gRPC

* Built on HTTP/2 with new features like server pushing and multiplexing
* Uses protocol buffers (schema, binary format) as message format, faster. streaming communication
* Efficient, suitable for microservices, video streaming, high-throughput
* Less human-readable, needs HTTP/2 support

#### SOAP simple object access protocol

* A protocol for exchanging information, relies on XML, uses HTTP, SMTP.
* Strict structure, envelope, header, body
* Can be both stateful (keep a session) or stateless
* Extensibility and built-in security (ws-security)

| Feature                | **REST**                                | **SOAP**                   | **GraphQL**                           | **gRPC**                             |
| ---------------------- | --------------------------------------- | -------------------------- | ------------------------------------- | ------------------------------------ |
| **Protocol Type**      | Architectural style                     | Protocol                   | Query language for APIs               | RPC framework                        |
| **Message Format**     | JSON (XML possible)                     | XML                        | JSON                                  | Protocol Buffers (binary format)     |
| **Transport Protocol** | HTTP/HTTPS                              | HTTP, SMTP                 | HTTP/HTTPS                            | HTTP/2                               |
| **Performance**        | Moderate, text-based (JSON)             | Slower, XML-heavy          | Efficient with tailored responses     | High performance, low latency        |
| **Statefulness**       | Stateless (typically)                   | Stateful or Stateless      | Stateless (client-driven queries)     | Stateful (supports streaming)        |
| **Use Cases**          | Public APIs, Web, Mobile, Microservices | Enterprise, Legacy Systems | Real-time apps, Flexible Data Queries | Microservices, Low-latency apps, IoT |
| **Security**           | HTTPS, OAuth, JWT, API Keys             | WS-Security (built-in)     | HTTPS, OAuth, JWT                     | SSL/TLS, OAuth, JWT                  |

### Authentication

**1. Basic**

* Username & pass, weak if not encrypted, and vulnerable to brute-force attacks

**2.Token-based**

* **JWT** - Json web token.
    * Steps: User logs in -> server generates a JWT -> sent to client -> add the JWT in the request header for future requests
* Stateless, scalable, easy to use for APIs, but difficult to revoke a token before its expiration time. This will need extra work with a Cache layer to set a TTL or remove the token manually (add to a blacklist).

**3. OAuth 2.0**

* OAuth is a protocol for authorization, requires HTTPS while OAuth 1.0a did not. Allows an app to access data on behalf of a user without exposing their credentials, like connecting to a website using a Google account.
* Secure and user-friendly, but tokens are short-lived and must be renewed, often by using a refresh token to obtain a new access token without requiring the user to log in again.

**4. 2FA 2-Factor Authentication**

* Needs 2 forms of verification, e.g., password + verification code.

**5. SSO Single Sign-On**

* One login for multiple apps

**6. SSH**

* Secure access over a non-secure network, for logging into a remote machine

### API gateway

* A server that acts as an API entry point for clients, **Single entry point**
* Functions:
    * Routing
    * Aggregating requests (e.g., duplicate requests into a single response)
    * Security, providing authentication and authorization
    * Rate limiting, load balancing, monitoring, logging
    * Caching
* Works with HTTP/HTTPS, REST, GraphQL, or even WebSockets.
* E.g., AWS API Gateway, Nginx (open-source)

## Caching

#### Cache Invalidation Strategies

* **Cache-Aside:** The application code is responsible for managing the cache.
    -   **Read:** Application checks the cache first. If it's a **miss**, it reads from the database and then writes the result into the cache for the next time.
    -   **Write:** Application writes directly to the database and then **invalidates (removes)** the corresponding entry from the cache.
* Read-through
    * Cache manages updates, the client only reads from the cache
* Write-through
    * Cache manages updates to the cache then the DB
* Write-behind
    * Async calls for DB update

Consider: update cache first or DB;

#### Reliability issue

* **Cache avalanche**: caches expired within a short time window, leading to a sudden huge request to data storage. Cascading failures, DB dead because of all requests, while requests are still pending, and causing DB to die again after reboot.
    * Solution: adds a random value to the base time-to-live
    * Leader-follower mode => use a cluster (e.g., 3 pairs of master-follower nodes)
* **Cache stampede (breakdown)**: a sudden influx of requests to the cache for the same key and getting cache misses
    * Use a worker to update TTL for hot keys
    * Lock the key for the first request and until it's updated.
* Both will lead to requests on data storage and eventually a system crash.
    * Use consistent hashing to distribute cache entries across multiple cache servers
    * Use a circuit breaker to avoid more server issues.
    * Use rate limiting to control request frequency
* **Cache penetration** : requests for a non-existing key, getting a cache miss then triggering a request to data storage.
    * Place a placeholder in the cache to represent the non-existing key.
    * Use a bloom filter
* Hot key: create replicates of hot keys and distribute them across the nodes to prevent a single node from being overloaded.
* Large key (value is huge): access to it will block bandwidth and slow down performance; partial modification will result in entire modification; reloading it is still time-consuming.

#### Bloom filters

* Uses hash functions and marks a bucket to 1. (e.g. "lemon" => {1, 3, 5})
* False positive: not existing but returns true. (e.g., "water" => {1, 2}, "cat" with values {2, 3} will return true while it should be false)

### Browser caching

### Server caching

* Individual data, the server first checks the cache before querying the DB
* Write-Around Cache
* Write-Back Cache, syncs from time to time
* Eviction policies:
    * LRU, Least Recently Used
    * FIFO, First In First Out
    * LFU, Least Frequently Used

### CDN Content Delivery Network

* For static resources like images, CSS files, videos.
* Reduces latency, provides high availability, and improved security.
* Expensive!! Normally needs a worker to update popular resources on the CDN.

## Proxy servers

### Forward proxy:

Proxy evaluates a user request to see whether to forward it. If so, it hides the client's identity. Used for things like internet use control to restrict requests, or caching popular websites.

### Reverse proxy

Hides the server's identity, e.g., load balancers, Firewalls to filter unwanted traffic, CDNs, SSL offloading

* Open proxy
* Transparent proxy
* Anonymous proxy
* Distorting proxy
* High anonymity

#### Load balancer

A reverse proxy. Open-source: **Nginx**, or vendor-provided like AWS ELB, GCP's Load Balancer.

**Algos**

* Round Robin: sent to different service instances in sequential order
* Least Connections: sent to the service instance with the fewest concurrent connections.
* Least response time
* IP hash : important for apps to make sure a user always connects to the same server
* Weighted algos : for servers with different hardware
* Geographic
* **Consistent hashing**
    * Hash key % N => N is server number (modulo operation)
    * Choose the server clockwise.
    * Use virtual nodes to solve unbalanced partitions.

**Type of balance**

* Layer 4, like at the TCP / IP layer, quicker but less flexible
* Layer 7, application level, can route requests to specific servers. But it's more expensive, involving decrypting a request between the client and LB, then encrypting a new request to the server.

**A load balancer is usually also a single point of failure**, to solve that:

* Multiple LBs
* Or a backup LB

## Database

### Types

* RDB: ACID principle
* NoSQL, easy to scale, flexible on structures.
    * Key-value: Redis, AWS DynamoDB
    * Graph: Neo4j
    * Document: MongoDB
    * Wide-column: Cassandra
    * Object store, blob: AWS S3

### How to choose

* Data models / schemas in the system. How are the relations between tables (foreign keys)?
* Query patterns that may be executed. Do they use a lot of joins, or filters on non-indexed attributes etc.?
* Consistency levels VS Availability. Which is more important?
* Data size. RDBs normally don't have sharding implemented, but NoSQL supports replication / sharding natively.

### Replications

Leader-follower mode enables read-write separation. Writes go to the **leader** node. Read-heavy traffic can be distributed to the **follower** nodes to separate workloads (Read-Write separation).

* Async replication: scheduled or a few seconds later, can have inconsistent data between replicas.
* Sync replication, updates followers at each update.

### Sharding

* Sharding keys: userid, name, geo, etc. A trade-off between hot keys VS retrieving data across multiple shards.

## Message broker