// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma

import cats.effect.IO
import cats.effect.SyncIO
import cats.effect.unsafe.implicits.global

import scala.Conversion
import scala.annotation.targetName
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

package object broadcastchannel {

  type OnMessageHandler[T] =
    (js.ThisFunction1[ /* this */ BroadcastChannel[Any], /* ev */ T, Any]) | Null

  @JSImport("broadcast-channel", "BroadcastChannel")
  @js.native
  class BroadcastChannel[T](val name: String) extends js.Object {

    def close(): js.Promise[Unit] = js.native

    val id: Double = js.native

    val isClosed: Boolean = js.native

    var onmessage: OnMessageHandler[T] = js.native

    def postMessage(msg: T): js.Promise[Unit] = js.native
  }

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
