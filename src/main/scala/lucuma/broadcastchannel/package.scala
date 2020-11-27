package lucuma

import scala.scalajs.js

package object broadcastchannel {
  
  type BroadcastChannel[T <: js.Object] = broadcastChannel.mod.BroadcastChannel[T]
}

