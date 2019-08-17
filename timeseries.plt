# Let's output to a jpeg file
set terminal jpeg size 500,500
# This sets the aspect ratio of the graph
set size 1, 1
# Where to place the legend/key
set key left top
# Draw gridlines oriented on the y axis
set grid y
# Specify that the x-series data is time data
set xdata time
# Specify the *input* format of the time data
set timefmt "%s"
# Specify the *output* format for the x-axis tick labels
set format x "%S"
# Label the x-axis
set xlabel 'seconds'
# Label the y-axis
set ylabel "response time (ms)"
# Tell gnuplot to use tabs as the delimiter instead of spaces (default)
set datafile separator '\t'
set output "graphs/hello.jpg"
set title "Benchmark /hello"
# Plot the data
plot "data/hello-c10-java.tsv" every ::2 using 2:5 title 'java' with points, \
     "data/hello-c10-laravel.tsv" every ::2 using 2:5 title 'laravel' with points, \
     "data/hello-c10-go.tsv" every ::2 using 2:5 title 'go' with points, \
     "data/hello-c10-node.tsv" every ::2 using 2:5 title 'node' with points, \
     "data/hello-c10-dotnet.tsv" every ::2 using 2:5 title 'dotnet' with points, \
     "data/hello-c10-python-debug.tsv" every ::2 using 2:5 title 'python' with points, \
     "data/hello-c10-python-gunicorn.tsv" every ::2 using 2:5 title 'python' with points, \
     "data/hello-c10-python-falcon.tsv" every ::2 using 2:5 title 'python' with points
set output "graphs/compute.jpg"
set title "Benchmark /compute"
# Plot the data
plot "data/compute-c10-java.tsv" every ::2 using 2:5 title 'java' with points, \
     "data/compute-c10-laravel.tsv" every ::2 using 2:5 title 'laravel' with points, \
     "data/compute-c10-go.tsv" every ::2 using 2:5 title 'go' with points, \
     "data/compute-c10-node.tsv" every ::2 using 2:5 title 'node' with points, \
     "data/compute-c10-dotnet.tsv" every ::2 using 2:5 title 'dotnet' with points, \
     "data/compute-c10-python-debug.tsv" every ::2 using 2:5 title 'python' with points, \
     "data/compute-c10-python-gunicorn.tsv" every ::2 using 2:5 title 'python' with points, \
     "data/compute-c10-python-falcon.tsv" every ::2 using 2:5 title 'python' with points
set output "graphs/countries.jpg"
set title "Benchmark /countries"
# Plot the data
plot "data/countries-c10-java.tsv" every ::2 using 2:5 title 'java' with points, \
     "data/countries-c10-laravel.tsv" every ::2 using 2:5 title 'laravel' with points, \
     "data/countries-c10-go.tsv" every ::2 using 2:5 title 'go' with points, \
     "data/countries-c10-node.tsv" every ::2 using 2:5 title 'node' with points, \
     "data/countries-c10-dotnet.tsv" every ::2 using 2:5 title 'dotnet' with points, \
     "data/countries-c10-python-debug.tsv" every ::2 using 2:5 title 'python' with points, \
     "data/countries-c10-python-gunicorn.tsv" every ::2 using 2:5 title 'python' with points, \
     "data/countries-c10-python-falcon.tsv" every ::2 using 2:5 title 'python' with points
set output "graphs/users.jpg"
set title "Benchmark /users"
# Plot the data
plot "data/users-c10-java.tsv" every ::2 using 2:5 title 'java' with points, \
     "data/users-c10-laravel.tsv" every ::2 using 2:5 title 'laravel' with points, \
     "data/users-c10-go.tsv" every ::2 using 2:5 title 'go' with points, \
     "data/users-c10-node.tsv" every ::2 using 2:5 title 'node' with points, \
     "data/users-c10-dotnet.tsv" every ::2 using 2:5 title 'dotnet' with points, \
     "data/users-c10-python-debug.tsv" every ::2 using 2:5 title 'python' with points, \
     "data/users-c10-python-gunicorn.tsv" every ::2 using 2:5 title 'python' with points, \
     "data/users-c10-python-falcon.tsv" every ::2 using 2:5 title 'python' with points
exit
