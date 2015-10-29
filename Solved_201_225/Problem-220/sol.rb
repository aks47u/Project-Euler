$Values = {}

def move(direction, location)
  case direction
  when :up then location[1] += 1
  when :down then location[1] -= 1
  when :right then location[0] += 1
  when :left then location[0] -= 1
  end
end

def turn_left(direction)
  case direction
  when :up then return :left
  when :down then return :right
  when :right then return :up
  when :left then return :down
  end
end

def turn_right(direction)
  case direction
  when :up then return :right
  when :down then return :left
  when :right then return :down
  when :left then return :up
  end
end

def seek(n,steps)
  def recurse_a(n,steps,location,direction)
    return location if steps == 0

    if n>0
      return recurse_a(n-1,steps,location,direction) if steps <= step_a(n-1,direction)[2]
      direction, location_offset, steps_offset = step_a(n-1,direction)
      location[0]+=location_offset[0]; location[1]+=location_offset[1];
      steps -= steps_offset
      return location if steps == 0
    end

    direction = turn_right(direction)

    if n>0
      return recurse_b(n-1,steps,location,direction) if steps <= step_b(n-1,direction)[2]
      direction, location_offset, steps_offset = step_b(n-1,direction)
      location[0]+=location_offset[0]; location[1]+=location_offset[1];
      steps -= steps_offset
      return location if steps == 0
    end

    move(direction,location)
    steps -= 1

    return location if steps == 0
    direction = turn_right(direction)
    raise "very strange"
  end

  def recurse_b(n,steps,location,direction)
    return location if steps == 0
    direction = turn_left(direction)
    move(direction,location)
    steps -= 1
    return location if steps == 0

    if n>0
      return recurse_a(n-1,steps,location,direction) if steps <= step_a(n-1,direction)[2]
      direction, location_offset, steps_offset = step_a(n-1,direction)
      location[0]+=location_offset[0]; location[1]+=location_offset[1];
      steps -= steps_offset
      return location if steps == 0
    end

    direction = turn_left(direction)
    return recurse_b(n-1,steps,location,direction) if n>0 and steps <= step_b(n-1,direction)[2]
    return location if steps == 0
    raise "very strange"
  end

  location = [0,1]
  steps -= 1
  direction = :up
  recurse_a(n,steps,location,direction)
end

def step_a(n, direction)
  return $Values[[:a,n,direction]] unless $Values[[:a,n,direction]] == nil
  old_direction = direction
  location = [0,0]

  if n == 0
    direction = turn_right(direction)
    move(direction,location)
    direction = turn_right(direction)
    $Values[[:a,n,old_direction]] = [direction,location.dup,1]
    return [direction,location.dup,1]
  end

  direction, offset, steps = step_a(n-1,direction)
  location[0] += offset[0]; location[1] += offset[1]
  direction = turn_right(direction)
  direction, offset, more_steps = step_b(n-1,direction)
  steps += more_steps
  location[0] += offset[0]; location[1] += offset[1]
  move(direction,location)
  steps += 1
  direction = turn_right(direction)
  $Values[[:a,n,old_direction]] = [direction,location.dup,steps]
  return [direction,location.dup,steps]
end

def step_b(n, direction)
  old_direction = direction
  location = [0,0]
  return $Values[[:b,n,direction]] unless $Values[[:b,n,direction]] == nil

  if n == 0
    direction = turn_left(direction)
    move(direction,location)
    direction = turn_left(direction)
    $Values[[:b,n,old_direction]] = [direction,location.dup,1]
    return [direction,location.dup,1]
  end

  direction = turn_left(direction)
  move(direction,location)
  steps = 1
  direction, offset, more_steps = step_a(n-1,direction)
  steps += more_steps
  location[0] += offset[0]; location[1] += offset[1]
  direction = turn_left(direction)
  direction, offset, more_steps = step_b(n-1,direction)
  steps += more_steps
  location[0] += offset[0]; location[1] += offset[1]
  $Values[[:b,n,old_direction]] = [direction,location.dup,steps]
  return [direction,location.dup,steps]
end

puts seek(50,10**12).inspect
