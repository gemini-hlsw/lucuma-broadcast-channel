// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma

import cats.effect.IO
import cats.effect.SyncIO
import cats.effect.unsafe.implicits.global

import scala.scalajs.js

package object broadcastchannel {

  type BroadcastChannel[T] = lucuma.bc.broadcastChannel.mod.BroadcastChannel[T]
  type OnMessageHandler[T] = lucuma.bc.broadcastChannel.broadcastChannelMod.OnMessageHandler[T]

  implicit def ioToOnMessageHandler[T](f: T => IO[Unit]): OnMessageHandler[T] =
    js.ThisFunction.fromFunction2(
      (_: lucuma.bc.broadcastChannel.broadcastChannelMod.BroadcastChannel[Any], x: T) =>
        f(x).unsafeRunAndForget(): js.Any
    ): OnMessageHandler[T]

  implicit def syncIOToOnMessageHandler[T](f: T => SyncIO[Unit]): OnMessageHandler[T] =
    ioToOnMessageHandler(x => IO(f(x).unsafeRunSync()))
}
