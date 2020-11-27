package lucuma

import scala.scalajs.js

package object broadcastchannel {

  type BroadcastChannel[T] = lucuma.bc.broadcastChannel.broadcastChannelMod.BroadcastChannel[T]
  type OnMessageHandler[T] = lucuma.bc.broadcastChannel.broadcastChannelMod.OnMessageHandler[T]

  implicit def ToOnMessageHandler[T, R <: js.Any](f: T => R): OnMessageHandler[T] =
    js.ThisFunction.fromFunction2(
      (
        _: BroadcastChannel[js.Any],
        x: T
      ) => f(x)
    ): OnMessageHandler[T]

}
