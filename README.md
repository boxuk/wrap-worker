
# Worker Ring Middleware

Ring middleware which uses Worker to handle identical simultaneous requests.

## Usage

```clojure
(ns my.project
  (:use [wrap-worker :only [wrap-worker]])
  (:require (compojure [handler :as handler])))

(-> #'app-routes
  (wrap-worker)
  (handler/site))
```

## How It Works

The middleware will (for GET requests only) check the URI and any parameters,
and use these to pass to Worker to handle identical requests with the same
process.

