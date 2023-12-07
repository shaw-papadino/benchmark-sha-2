# Usage
## scala
```bash
$ scala HashBenchmark.scala "Hello, this is a sample text to hash." 1000 SHA-256

SHA-256 hash times percentiles:
50 percentile: 16923 nanoseconds
90 percentile: 29773 nanoseconds
95 percentile: 34856 nanoseconds
99 percentile: 64995 nanoseconds
```

## scala-cli
```
$ scala-cli HashBenchmark.scala -- "Hello, this is a sample text to hash." 1000 SHA-256
Hello, this is a sample text to hash. 1000 SHA-256
SHA-256 hash times percentiles:
50 percentile: 18238 nanoseconds
90 percentile: 31484 nanoseconds
95 percentile: 36181 nanoseconds
99 percentile: 68666 nanoseconds
```
