# Let's output to a jpeg file
set terminal jpeg size 500,500
# This sets the aspect ratio of the graph
set size 1, 1
set style line 1 lc rgb "red"
set style line 2 lc rgb "blue"
set style fill solid
set boxwidth 0.5
set ylabel 'rps'
set xtics rotate by -45
set grid ytics linestyle 1
mycolor(x) = ((x*11244898) + 2851770)


set output "graphs/rps-hello.jpg"
set title "requests per second /hello"
plot "data/rps-hello.dat" using 0:2:(mycolor($0)):xtic(1) with boxes lc rgb variable notitle

set output "graphs/rps-compute.jpg"
set title "requests per second /compute"
plot "data/rps-compute.dat" using 0:2:(mycolor($0)):xtic(1) with boxes lc rgb variable notitle

set output "graphs/rps-countries.jpg"
set title "requests per second /countries"
plot "data/rps-countries.dat" using 0:2:(mycolor($0)):xtic(1) with boxes lc rgb variable notitle

set output "graphs/rps-users.jpg"
set title "requests per second /users"
plot "data/rps-users.dat" using 0:2:(mycolor($0)):xtic(1) with boxes lc rgb variable notitle
