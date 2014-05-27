for {set sum 0; set i 3} {$i < 1000} {incr i} {
    incr sum [expr {($i % 5 == 0 || $i % 3 == 0) * $i}]
}
puts $sum
