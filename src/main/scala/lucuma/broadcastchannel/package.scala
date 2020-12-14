package lucuma

import cats.effect.IO
import scala.scalajs.js

package object broadcastchannel {

  type BroadcastChannel[T] = lucuma.bc.broadcastChannel.mod.BroadcastChannel[T]
  type OnMessageHandler[T] = lucuma.bc.broadcastChannel.broadcastChannelMod.OnMessageHandler[T]

  implicit def ToOnMessageHandler[T](f: T => IO[Unit]): OnMessageHandler[T] =
    js.ThisFunction.fromFunction2(
      (_: lucuma.bc.broadcastChannel.broadcastChannelMod.BroadcastChannel[js.Any], x: T) =>
        f(x).unsafeRunAsyncAndForget(): js.Any
    ): OnMessageHandler[T]
}
