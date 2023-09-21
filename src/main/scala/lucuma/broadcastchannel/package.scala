// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma

import cats.effect.IO
import cats.effect.SyncIO
import cats.effect.unsafe.implicits.global

import scala.Conversion
import scala.annotation.targetName
import scala.scalajs.js

package object broadcastchannel {

  type BroadcastChannel[T] = lucuma.bc.broadcastChannel.typesBroadcastChannelMod.BroadcastChannel[T]
  type OnMessageHandler[T] = lucuma.bc.broadcastChannel.typesBroadcastChannelMod.OnMessageHandler[T]

  given [T]: Conversion[T => IO[Unit], OnMessageHandler[T]] = (f: T => IO[Unit]) =>
    ioToOnMessageHandler(f)

  @targetName("syncToOnMessage")
  given [T]: Conversion[T => SyncIO[Unit], OnMessageHandler[T]] = (f: T => SyncIO[Unit]) =>
    syncIOToOnMessageHandler(f)

  def ioToOnMessageHandler[T](f: T => IO[Unit]): OnMessageHandler[T] =
    js.ThisFunction.fromFunction2((_: BroadcastChannel[Any], x: T) =>
      f(x).unsafeRunAndForget(): js.Any
    ): OnMessageHandler[T]

  def syncIOToOnMessageHandler[T](f: T => SyncIO[Unit]): OnMessageHandler[T] =
    ioToOnMessageHandler(x => IO(f(x).unsafeRunSync()))
}
